using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class PlayerController : MonoBehaviour
{
    //Zıplama için yazıldı karekterin altında bulunan nesne ile yer temas olması için
    [SerializeField]
    LayerMask groundMask;

    //Karekter yürümü vb şeyler için tanımlandı
    [SerializeField]
    CharacterController controller;

    //Karekter altındaki nesnenin transformu için
    [SerializeField]
    Transform groundCheck;

    //Masaüstünde kullanırken ekrana mesaj verdirmek için
    [SerializeField]
    Text txtInfo;

    //Ekranda bulunan buttonlar
    [SerializeField]
    Button btnJump, btnPress, btnUp, btnDown, btnLeft, btnRight;

    //Yere düşme ve yürüme miktarı için tanımlandı
    private float playerSpeed = 3.5f, gravity = -9.81f;
    //Yer ile alttaki nesne arasındaki mesafeyi ve zıplama değerini değişkenlere atadık
    private float groundDistance = 0.4f, jumpHeight = 1f;
    //Yer ile temas var mı durumu
    private bool isGrounded;
    //Karekterin velocity değerlerini almak için tanımlandı
    private Vector3 velocity;
    //X ve Z komunları ve yürümü hızı
    private float x, z, pressSpeed = 1.5f;
    //Kasanın açıldığı, anahtarın alındığı, kapının oluşturu ve anahtar kesin olarak alındı mı durumları
    public bool chestState, keyState, doorState, rewardedKey = false;
    //Bilgisayar ve mobil kontrolü için tanımlandı bilgisayar için true mobil için false
    private bool pcControlState = false;
    //Seçilen anahtar kapı ve sandık
    private GameObject selectedChest, selectedKey, selectedDoor;
    //Haritada bulunan toplam sandık sayısı
    private int chestAmount, lastStage;
    //Sandık miktarları diziye atıldı
    private int[] chestAmounts = {
        3,
        3,
        4,
        4,
        5
    };
    //Bölüm isimleri diziye atıldı
    private string[] stageNames = {
        "Stage1",
        "Stage2",
        "Stage3",
        "Stage4",
        "Stage5"
    };
    //Karekterin başlangıç pozisyonu buradan alınıyor
    private PlayerPositionController playerPos;

    void Start()
    {
        //Son geçilen bölüm bilgisi alındı ve ilgili kontrolden sonra değişkene atandı
        lastStage = PlayerPrefs.GetInt("LastStage", 1);
        if (lastStage >= 5)
            lastStage = 5;
        chestAmount = chestAmounts[(lastStage - 1)];

        //Mesaj ifadesinin kapatıldığı yer
        txtInfo.gameObject.SetActive(false);
        //PlayerPoisitionController i initialize ettik
        playerPos = GameObject.FindGameObjectWithTag("PlayerPos")
            .GetComponent<PlayerPositionController>();

        //Bilgisayardan oynanmayacak ise oluşacak ifadeler
        if (!pcControlState)
        {
            btnUp.gameObject.SetActive(true);
            btnDown.gameObject.SetActive(true);
            btnLeft.gameObject.SetActive(true);
            btnRight.gameObject.SetActive(true);
            btnJump.gameObject.SetActive(true);
            btnJump.onClick.AddListener(jumpPlayer);
            btnPress.onClick.AddListener(pressBtn);
        }
    }

    private void FixedUpdate()
    {
        //Karekter bir miktar aşağı düştüyse yine başa ışınlıyacak
        if (transform.localPosition.y < -10)
            playerPos.setPlayerPos();
    }

    void Update()
    {
        //Yerde mi kontrolü
        isGrounded = Physics.CheckSphere(
            groundCheck.position, groundDistance, groundMask);
 
        if (isGrounded && velocity.y < 0)
            velocity.y = -2f;

        //Bilgisayardan girildiyse oyuna
        if (pcControlState)
        {
            x = Input.GetAxis("Horizontal");
            z = Input.GetAxis("Vertical");
        }

        //Karekterin yürümesini sağladığımız yer
        Vector3 move = transform.right * x + transform.forward * z;
        controller.Move(move * playerSpeed * Time.deltaTime);

        //Karekterin düşmesi için yazdığımız yer
        velocity.y += gravity * Time.deltaTime;
        controller.Move(velocity * Time.deltaTime);

        //Bilgisayardan girildiyse, karekter yerdeyse ve jump basıldıysa çalışır
        if (pcControlState)
        {
            //Karekter zıplar
            if (Input.GetButtonDown("Jump") && isGrounded)
                velocity.y = Mathf.Sqrt(jumpHeight * -2f * gravity);
        }

        if (pcControlState && Input.GetKeyDown(KeyCode.E) && chestState) //Chest Açımı için yazılan kontrol
            chestProgress();
        else if (pcControlState && Input.GetKeyDown(KeyCode.E) && keyState) //Anahtar alımı için yazılan kontrol
            keyProgress();
        else if (pcControlState && Input.GetKeyDown(KeyCode.E) && doorState) //Kapının görünmesi için yazılan kontrol
            doorProgress();
    }

    //Button araıcılığı ile zıplama
    private void jumpPlayer()
    {
        if (isGrounded)
            velocity.y = Mathf.Sqrt(jumpHeight * -2f * gravity);
    }

    //Kapı açma - sandık açma - anahtar alma için kullanılan metot
    private void pressBtn()
    {
        if (chestState)
            chestProgress();
        else if (keyState)
            keyProgress();
        else if (doorState)
            doorProgress();
    }

    //Sandık açılınca olacaklar
    private void chestProgress()
    {
        //Chest anim oynar
        chestState = false;
        selectedChest.GetComponent<ChestController>()
            .GetComponent<Animator>().SetBool("ChestState", true);

        //Chest hala kalmışsa 1 den fazla
        if (chestAmount != 1)
        {
            if (Random.Range(1, (chestAmount + 1)) == 1) //Random anahtar verir
            {
                selectedKey.SetActive(true);
                selectedKey.transform.position = transform.position;
            }
            else //Anahtar gelmezse çalışır
            {
                chestAmount--;
                selectedKey.SetActive(false);
            }
        }
        else //Son sandık kalmışsa çalışır
            selectedKey.transform.position = transform.position;

        //Chestin açıldığı durumu true yapar böylece bir daha basılamaz
        selectedChest.GetComponent<ChestController>().chestOpenState = true;
        //Ekrandaki yazıyı kapatır
        txtInfo.gameObject.SetActive(false);
    }

    //Anahtar için yazılan metod. Ekrandaki yazıyı kapatır - Kapıyı aktif eder - anahtar alındı işaretler
    private void keyProgress()
    {
        keyState = false;
        selectedKey.gameObject.SetActive(false);
        rewardedKey = true;
        txtInfo.gameObject.SetActive(false);
        selectedDoor.SetActive(true);
    }

    //Kapı için yazılan metod yeni bölüme geçme ve geçilen bölümü kayıt etme
    private void doorProgress()
    {
        doorState = false;
        PlayerPrefs.SetInt("LastStage", (lastStage + 1));
        if (lastStage <= (stageNames.Length - 1))
            SceneManager.LoadScene(stageNames[lastStage]);
        else
            SceneManager.LoadScene("StageScene");
        Cursor.lockState = CursorLockMode.Locked;
    }

    //Seçilen sandığın atandığı metod
    public void setChest(GameObject chest, GameObject key, GameObject door)
    {
        chestState = true;
        if (pcControlState)
            txtInfo.gameObject.SetActive(true);
        else
            btnPress.gameObject.SetActive(true);
        selectedChest = chest;
        selectedKey = key;
        selectedDoor = door;
    }

    //Sandıktan uzaklaşınca çalışır
    public void unSetChest()
    {
        chestState = false;
        if (pcControlState)
            txtInfo.gameObject.SetActive(false);
        else
            btnPress.gameObject.SetActive(false);
    }

    //Seçilen anahtarın atandığı metod
    public void setKey(GameObject key)
    {
        keyState = true;
        if (pcControlState)
            txtInfo.gameObject.SetActive(true);
        else
            btnPress.gameObject.SetActive(true);
        selectedKey = key;
    }

    //Anahtardan uzaklaşınca çalışır
    public void unSetKey()
    {
        keyState = false;
        if (pcControlState)
            txtInfo.gameObject.SetActive(false);
        else
            btnPress.gameObject.SetActive(false);
    }

    //Seçilen kapının atandığı metod
    public void setDoor()
    {
        doorState = true;
        if (pcControlState)
            txtInfo.gameObject.SetActive(true);
        else
            btnPress.gameObject.SetActive(true);
    }

    //Kapıdan uzaklaşınca çalışır
    public void unSetDoor()
    {
        doorState = false;
        if (pcControlState)
            txtInfo.gameObject.SetActive(false);
        else
            btnPress.gameObject.SetActive(false);
    }

    //Sağa yürüme
    public void goRight()
    {
        x = pressSpeed;
    }

    //Sola yürüme
    public void goLeft()
    {
        x = -pressSpeed;
    }

    //İleri yürüme
    public void goUp()
    {
        z = pressSpeed;
    }

    //Geri yürüme
    public void goDown()
    {
        z = -pressSpeed;
    }

    //İleri ve geri yürüme bırakılırsa çalışır
    public void leftUpAndDown()
    {
        z = 0;
    }

    //Sağa ve sola yürüme bırakılırsa çalışır
    public void leftLeftAndRight()
    {
        x = 0;
    }
}
