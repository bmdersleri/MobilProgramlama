using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Material_Instance : MonoBehaviour
{
    [SerializeField]
    private Material material;
    private SpriteRenderer wallSpriteRenderer;
    private Animator wallDissolveAnim;
 //   private int w_Mor=0, w_Mavi=0, w_Sari=0, w_Kirmizi=0, w_Turuncu=0;
    void Start()
    {
        
        material = gameObject.GetComponent<SpriteRenderer>().material;//Her yeni prefab'da Material instance oluşturur. Böylece bir objenin materyalinde yapılan değişiklik
                                                                      //sadece o objeyi etkiler.
        wallSpriteRenderer = gameObject.GetComponent<SpriteRenderer>();
        wallDissolveAnim = this.gameObject.GetComponent<Animator>();
    }

    void Update()
    {
        TagDegistir();
        if (wallDissolveAnim.GetCurrentAnimatorStateInfo(0).IsTag("geriGel"))
        {
            wallDissolveAnim.SetBool("fadeDown", false);
            wallDissolveAnim.SetBool("fadeUp", false);
        }
        //Player'ın hızı sıfır olduğunda,yandığında tüm objeleri yok et.
        if (SceneManager.GetActiveScene().name!="Menu")
        {
            if (Player.Instance.speed == 0)
            {
                if(this.gameObject.tag!="Player")
                wallDissolveAnim.SetBool("fadeDown", true);
                Player.Instance.GetComponent<Animator>().SetBool("bUp", true);                
            }
        }
    }

    void TagDegistir()
    {
        string wallName = wallSpriteRenderer.sprite.name;
        if (wallName == "wallKirmizi")
        {
            gameObject.tag = "wallKirmizi";
        }
        if (wallName == "wallMor")
        {
            gameObject.tag = "wallMor";
        }
        if (wallName == "wallSari")
        {
            gameObject.tag = "wallSari";
        }
        if (wallName == "wallMavi")
        {
            gameObject.tag = "wallMavi";
        }
        if (wallName == "wallTuruncu")
        {
            gameObject.tag = "wallTuruncu";
        }
    }
}
