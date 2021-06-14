using UnityEngine;
using System.Collections;
using System.IO;
using NaughtyAttributes;

[HelpURL("https://gitlab.com/hifive-games/hifive-game-dev-framework/hifive-game-template/-/wikis/Hifive-Game-Template---Home-Wiki#screen-capture-tool")]
public class ScreenCapture : MonoBehaviour
{
	#if UNITY_EDITOR
	
    private static ScreenCapture Instance { get; set; }
    
    [InfoBox("Usage: Press \"s\" when you want to capture the screen. CLICK QUESTION MARK (?) BUTTON for more.", EInfoBoxType.Normal)]

    [Header ("Prefix for capture files.")]
    public string fileName = "screenshoot";
    private Texture2D texture;

    //do not destroy object when changing or reloading scenes
	void Awake(){
		if (Instance){
			Destroy (gameObject);
		} else {
			Instance = this;
			DontDestroyOnLoad (gameObject);
		}
	}

    //listen for keyboard button press
	void Update(){
        //you can change selected keyboard button for different favorite button
		if (Input.GetKeyDown("s"))
			StartCoroutine(Capture());
	}

    //capture screen
    IEnumerator Capture() {
		//wait for graphics to render
		yield return new WaitForEndOfFrame();

		// create a texture to pass to encoding
		texture = new Texture2D(Screen.width, Screen.height, TextureFormat.RGB24, false);

		//put buffer into texture
		texture.ReadPixels(new Rect(0, 0, Screen.width, Screen.height), 0, 0);
		texture.Apply();

		//split the process up--ReadPixels() and the GetPixels() call inside of the encoder are both pretty heavy
		yield return 0;

		byte[] bytes = texture.EncodeToPNG();
        //sufix for filename
		string timestamp = System.DateTime.Now.ToString ("dd_MM_yyyy_HH_mm_ss");

		//save image
        File.WriteAllBytes(Application.dataPath + "/../ScreenCapture/"+ fileName +"_" + timestamp + ".png", bytes);
	}

	#endif
}