using UnityEngine;
using UnityEngine.UI;

public class yeni : MonoBehaviour
{
    public Text txt;
    public Text basari;
    public GameObject spawnn;
    public GameObject spawnniki;
    public GameObject butonbir;
    public GameObject butoniki;
    public GameObject panell;
    public GameObject[] closedByButtons;
    public RectTransform solbuton;
    

    public bool OyunBasladi = false;
    
    AudioSource audiosrc;
    public Slider sldr;
    
    



    private void Start()
    {
        audiosrc = GetComponent<AudioSource>();
        this.gameObject.SetActive(true);
        solbuton.anchoredPosition = new Vector2(-495.4f, 155.8f);
        basari.text = "High Score: " + PlayerPrefs.GetInt("ToplanilanBayrak");


    }

    public void buton()
    {

        oyunkontrol.instance.OyunBasladi();
    }
        
    public void OyundanCikis()
    {
        Application.Quit();
    }
    public void Volume()
    {

        audiosrc.volume = sldr.value;
        
    }
    public void Options()
    {
        for (int i = 0; i < closedByButtons.Length; i++)
        {
            closedByButtons[i].gameObject.SetActive(false);
        }
        panell.gameObject.SetActive(true);

    }
    public void BacktoMenu()
    {
        for (int i = 0; i < closedByButtons.Length; i++)
        {
            closedByButtons[i].gameObject.SetActive(true);
        }
        panell.gameObject.SetActive(false);
    }
    public void ShowLeaderboardUI()
    {
        PlayGamesScript.ShowLeaderboardUI();
    }
   

}
