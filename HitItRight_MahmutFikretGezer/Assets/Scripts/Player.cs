using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class Player : MonoBehaviour
{
    [SerializeField]
    private Sprite[] randomBallSprites;
    public float speed;
    private Vector2 direction;
    [SerializeField]
    private Sprite[] randomWallSprites;
    private static Player instance;
    private List<GameObject> wallControl = new List<GameObject>();//top'un olduğu renkte en az 1 wall bulunması için.
    private int wallKirmizi = 0, wallSari = 0, wallMor = 0, wallMavi = 0, wallTuruncu = 0;
    [SerializeField]
    private GameObject[] carpmaEfekti;
    public bool objeceCarpti;//timerBarı kontrol etmek için eğer top doğru wall'a çarparsa süre artacak.
    [SerializeField]
    private GameObject canvasS;
    public bool hareketEttiMi;
    private Quaternion efektRotation;
    private Vector3 efektPosition;
    [SerializeField]
    private Text scoreText;
    [SerializeField]
    private TextMeshProUGUI afterGameScoreText;
    private int score;
    private int highScore;
    [SerializeField]
    private TextMeshProUGUI afterGameHighScoreText;
    private bool btnSolMu, btnYukariMi;
    [SerializeField]
    private GameObject endGameMenu;
    [SerializeField]
    private GameObject particleFrame;
    [SerializeField]
    private GameObject ingameUI;
    public AudioSource bgMusic;
    [SerializeField]
    private AudioSource wallHitSound;
    [SerializeField]
    private AudioSource endingSound;
    


    //Bu scripte erişmek için referans.(Material_Instance'dan erişildi.)
    public static Player Instance
    {
        get
        {
            if (instance == null)
            {
                instance = GameObject.FindObjectOfType<Player>();
            }
            return Player.instance;
        }
    }

    void Start()
    {
        bgMusic.volume = 0.6f;
        bgMusic.Play();
        score = 0;
        scoreText.text = score.ToString();

        afterGameHighScoreText.text = PlayerPrefs.GetInt("HighScore", 0).ToString();
        gameObject.GetComponent<SpriteRenderer>().sprite = randomBallSprites[Random.Range(0, randomBallSprites.Length)];//başlangıçta rastgele ball'a sprite atıyor.
        //Carpma Animasyonlarını Sıfırla
        carpmaEfekti[0].GetComponent<ParticleSystem>().Stop();
        carpmaEfekti[1].GetComponent<ParticleSystem>().Stop();
        carpmaEfekti[2].GetComponent<ParticleSystem>().Stop();
        carpmaEfekti[3].GetComponent<ParticleSystem>().Stop();
        carpmaEfekti[4].GetComponent<ParticleSystem>().Stop(); 
    }

    void Update()
    {
        Movement();
        //Eğer player'ın animatoründeki ballBrightnessDown oynadıysa, player'daki tüm animasyonları resetler.
        if (this.gameObject.GetComponent<Animator>().GetCurrentAnimatorStateInfo(0).IsTag("top_RenkDegisti"))
        {
            gameObject.GetComponent<Animator>().SetBool("bUp", false);
            gameObject.GetComponent<Animator>().SetBool("bDown", false);
        }

        if(Time.timeScale==0)
        {
            canvasS.SetActive(false);
        }
        else
        {
            canvasS.SetActive(true);
        }
    }

    void Movement()
    {
        if (Input.GetKeyDown(KeyCode.A))
        {
            direction = Vector2.left;
            hareketEttiMi = true;
        }
        if (Input.GetKeyDown(KeyCode.D))
        {
            direction = Vector2.right;
            hareketEttiMi = true;
        }
        if (Input.GetKeyDown(KeyCode.W))
        {
            direction = Vector2.up;
            hareketEttiMi = true;
        }
        if (Input.GetKeyDown(KeyCode.S))
        {
            direction = Vector2.down;
            hareketEttiMi = true;
        }

        //gameObject.GetComponent<Rigidbody2D>().AddForce(direction * speed);
        float amountOfMove = speed * Time.deltaTime;
        transform.Translate(direction * amountOfMove);

    }

    private void OnCollisionEnter2D(Collision2D other)
    {           
        //sprite larla işlem yaparken kolaylık sağlaması için
        string wallName = other.gameObject.GetComponent<SpriteRenderer>().sprite.name;
        string ballName = gameObject.GetComponent<SpriteRenderer>().sprite.name;
        
        //top kendi rengiyle doğru temas ediyorsa yapılacak işlemler
        if ((wallName == "wallKirmizi" && ballName == "ballKirmizi")
            || (wallName == "wallSari" && ballName == "ballSari")
            || (wallName == "wallMavi" && ballName == "ballMavi")
            || (wallName == "wallMor" && ballName == "ballMor")
            || (wallName == "wallTuruncu" && ballName == "ballTuruncu"))
        {
            wallHitSound.PlayOneShot(wallHitSound.clip);
            score += 1;
            scoreText.text = score.ToString();
            objeceCarpti = true;
            VarMi();

            //Çarpma efektini hangi bölgede, hangi rotation da olduğu belirle ve o bölgede oynat.
            if (other.gameObject.transform.position.x==-2.2f)
            {               
                efektPosition = new Vector3(-1.789f, other.gameObject.transform.position.y);
                efektRotation = Quaternion.Euler(new Vector3(0, 90, 0));
                if(wallName=="wallKirmizi")
                    EfektSpawn(0);
                if (wallName == "wallMavi")
                    EfektSpawn(1);
                if (wallName == "wallMor")
                    EfektSpawn(2);
                if (wallName == "wallSari")
                    EfektSpawn(3);
                if (wallName == "wallTuruncu")
                    EfektSpawn(4);
            }
            if (other.gameObject.transform.position.y == 3.44f)
            {
                efektPosition = new Vector3(other.gameObject.transform.position.x, 3.029f);
                efektRotation = Quaternion.Euler(new Vector3(90, 90, 0));
                if (wallName == "wallKirmizi")
                    EfektSpawn(0);
                if (wallName == "wallMavi")
                    EfektSpawn(1);
                if (wallName == "wallMor")
                    EfektSpawn(2);
                if (wallName == "wallSari")
                    EfektSpawn(3);
                if (wallName == "wallTuruncu")
                    EfektSpawn(4);
            }
            if (other.gameObject.transform.position.x == 2.2f)
            {
                efektPosition = new Vector3(1.789f, other.gameObject.transform.position.y);
                efektRotation = Quaternion.Euler(new Vector3(180, 90, 0));
                if (wallName == "wallKirmizi")
                    EfektSpawn(0);
                if (wallName == "wallMavi")
                    EfektSpawn(1);
                if (wallName == "wallMor")
                    EfektSpawn(2);
                if (wallName == "wallSari")
                    EfektSpawn(3);
                if (wallName == "wallTuruncu")
                    EfektSpawn(4);
            }
            if (other.gameObject.transform.position.y == -4.62f)
            {
                efektPosition = new Vector3(other.gameObject.transform.position.x, -4.209f);
                efektRotation = Quaternion.Euler(new Vector3(-90, 90, 0));
                if (wallName == "wallKirmizi")
                    EfektSpawn(0);
                if (wallName == "wallMavi")
                    EfektSpawn(1);
                if (wallName == "wallMor")
                    EfektSpawn(2);
                if (wallName == "wallSari")
                    EfektSpawn(3);
                if (wallName == "wallTuruncu")
                    EfektSpawn(4);
            }

            //ball için
            gameObject.GetComponent<Animator>().SetBool("bUp", true);
            gameObject.GetComponent<SpriteRenderer>().sprite = randomBallSprites[Random.Range(0, randomBallSprites.Length)];//Çarpışma sonrası player rengini değiştir.         
            gameObject.GetComponent<Animator>().SetBool("bDown", true);

            //wall için
            other.gameObject.GetComponent<Animator>().SetBool("fadeDown", true);//dissolve animationı
            //Topun renginde wall olup olmadığını kontrol eder, eğer yoksa ona göre wall'a sprite atar.
            if (wallKirmizi != 0 && wallMavi != 0 && wallTuruncu != 0 && wallSari != 0 && wallMor != 0)
            {
                other.gameObject.GetComponent<SpriteRenderer>().sprite = randomWallSprites[Random.Range(0, randomWallSprites.Length)];//wall'un rengini değiştirir
            }
            else
            {
                if (wallKirmizi == 0)
                {
                    other.gameObject.GetComponent<SpriteRenderer>().sprite = randomWallSprites[0];
                }
                else if (wallMavi == 0)
                {
                    other.gameObject.GetComponent<SpriteRenderer>().sprite = randomWallSprites[1];
                }
                else if (wallMor == 0)
                {
                    other.gameObject.GetComponent<SpriteRenderer>().sprite = randomWallSprites[2];
                }
                else if (wallSari == 0)
                {
                    other.gameObject.GetComponent<SpriteRenderer>().sprite = randomWallSprites[3];
                }
                else if (wallTuruncu == 0)
                {
                    other.gameObject.GetComponent<SpriteRenderer>().sprite = randomWallSprites[4];
                }
            }
            other.gameObject.GetComponent<Animator>().SetBool("fadeUp", true);//dissolve animationın tersi

            #region çarpışma sonrası butona basıldığında aksi yöne gidebilmesi için şarttı. Aksi taktirde ters yöne gitmek için 2 defa basmak gerekiyordu.
            if (direction==Vector2.left)
            {
                btnSolMu = false;
            }
            if (direction == Vector2.right)
            {
                btnSolMu = true;
            }
            if (direction == Vector2.up)
            {
                btnYukariMi = false;
            }
            if (direction == Vector2.down)
            {
                btnYukariMi = true;
            }
            #endregion
            direction *= -1;//top çarptığı zaman geldiği yönün tam tersi yönüne gider
          

            //wall'ları eklediğimiz diziyi temizliyoruz ki fazladan eleman olmasın.

        }
        else
        {
            bgMusic.Stop();
            endingSound.PlayOneShot(endingSound.clip);
            afterGameScoreText.text = scoreText.text;
            if(score>PlayerPrefs.GetInt("HighScore",0))
            {
                PlayerPrefs.SetInt("HighScore", score);
                afterGameHighScoreText.text = score.ToString();
            }            
            speed = 0;//yanlış köşeye çarpınca top durur.
            endGameMenu.SetActive(true);
           // particleFrame.SetActive(true);
            ingameUI.SetActive(false);
            
        }
        //Kontrol için oluşturduğumuz listeyi temizliyoruz ki fazladan wall eklenmesin ve kontrol yaparken yanlış değer vermesin.
        wallControl.Clear();
        //wall'ları control ettiğimiz değerleri sıfırlıyoruz.
        wallKirmizi = 0;
        wallSari = 0;
        wallMavi = 0;
        wallMor = 0;
        wallTuruncu = 0;
    }
    public void ButtonRightClicked()
    {
        if(btnSolMu)
        {
            direction = Vector2.right;
            btnSolMu = false;
        }
        else
        {
            direction = Vector2.left;
            btnSolMu = true;
        }
        float amountOfMove = speed * Time.deltaTime;
        gameObject.transform.Translate(direction * amountOfMove);
    }
    public void ButtonLeftClicked()
    {
        if (btnYukariMi)
        {
            direction = Vector2.down;
            btnYukariMi = false;
        }
        else
        {
            direction = Vector2.up;
            btnYukariMi = true;
        }
        float amountOfMove = speed * Time.deltaTime;
        //transform.Translate(direction * amountOfMove);
        gameObject.transform.Translate(direction * amountOfMove);
    }
    void VarMi()//Eğer top'un renginde wall olup olmadığını kontrol etmek için.
    {
        //Belirtilen taglerdeki wall'ları bulup listeye ekliyor.
        wallControl.AddRange(GameObject.FindGameObjectsWithTag("wallKirmizi"));
        wallControl.AddRange(GameObject.FindGameObjectsWithTag("wallMavi"));
        wallControl.AddRange(GameObject.FindGameObjectsWithTag("wallSari"));
        wallControl.AddRange(GameObject.FindGameObjectsWithTag("wallMor"));
        wallControl.AddRange(GameObject.FindGameObjectsWithTag("wallTuruncu"));
        Debug.Log(wallControl.Count);
        //Hangi renk wall'dan kaç tane olduğunu ölçer.
        for (int i = 0; i < wallControl.Count; i++)
        {
            if (wallControl[i].tag == "wallKirmizi")
            {
                wallKirmizi += 1;

            }
            if (wallControl[i].tag == "wallSari")
            {
                wallSari += 1;
            }
            if (wallControl[i].tag == "wallMor")
            {
                wallMor += 1;
            }
            if (wallControl[i].tag == "wallMavi")
            {
                wallMavi += 1;
            }
            if (wallControl[i].tag == "wallTuruncu")
            {
                wallTuruncu += 1;
            }
        }
        //Debug.Log("K:" + wallKirmizi + " S:" + wallSari + " Mo:" + wallMor + " Ma:" + wallMavi + " T:" + wallTuruncu);
    }

    void EfektSpawn(int i)//efekt oynatırken kolaylık olsun diye, collision içinde
    {
            carpmaEfekti[i].GetComponent<ParticleSystem>().Play();
            carpmaEfekti[i].transform.position = efektPosition;
            carpmaEfekti[i].transform.rotation = efektRotation;          
    }

}
