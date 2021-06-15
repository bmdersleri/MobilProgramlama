using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class oyun : MonoBehaviour
{
    public Button baslat;
    public Button ekle;
    public Button sil;
    public Text Sonuc;
    public Text giris;
    public Text sans;
    RectTransform yaziliste_rt;
    RectTransform yaziliste2_rt;
    RectTransform yazi_rt;
    RectTransform yazi2_rt;
    public GameObject cark;
    public GameObject parca;
    public GameObject referans;
    public GameObject referans2;
    GameObject yazi;
    GameObject yaziliste;
    public float bol = 0;
    float bolyaz = 90;
    int hesap = 1;
    float ilk = 0;
    int random;
    int rgb = 0;
    List<string> degerler = new List<string>();
    List<Color> colorList = new List<Color>()
    {
     new Color(0F, 0F, 0F),
     new Color(255F, 0F, 0F),
     new Color(0F, 255F, 0F),
     new Color(0F, 0F, 255F),
     new Color(255F, 255F, 0F),
     new Color(255F, 0F, 255F),
     new Color(0F, 255F, 255F),
     new Color(255F, 255F, 255F),
     new Color(0F, 0F, 0F)
    };
    public void liste()
    {
        Vector3 spawnkonum = new Vector3();
        spawnkonum.x = 0;
        spawnkonum.y = 1.3f;
        spawnkonum.z = 1;
        Destroy(parcalar);
        Destroy(yazi);
        Destroy(yaziliste);
        parcalar = new GameObject("parcalar");
        parcalar.transform.position = spawnkonum;
        yazi = new GameObject("yazilar");
        {
            yazi.transform.parent = this.yazilar.transform;
            yazi_rt = yazi.GetComponent<RectTransform>();
            yazi2_rt = referans.GetComponent<RectTransform>();
            yazi_rt.anchorMax = yazi2_rt.anchorMax;
            yazi_rt.anchorMin = yazi2_rt.anchorMin;
            yazi.transform.position = referans.transform.position;
        }
        yaziliiste = new GameObject("liste");
        {
            yaziliste.transform.parent = this.yazilar.transform;
            yaziliste.AddComponent<VerticalLayoutGroup>();
            yaziliste_rt = yaziliste.GetComponent<RectTransform>();
            yaziliste2_rt = referans2.GetComponent<RectTransform>();
            yaziliste_rt.anchorMax = yaziliste2_rt.anchorMax;
            yaziliste_rt.anchorMin = yaziliste2_rt.anchorMin;
            yaziliste.transform.position = referans2.transform.position;
        }
        bol = 360f / sayi;
        bolyaz = 90f - (bol / 2f);
        for (int q = 0; q < sayi; q++)
        {
            sans.text = ("" + degerler[q] + "");
            sans.color = colorList[rgb];
            Instantiate(parca, spawnkonum, Quaternion.Euler(0, 0, parcay), parcalar.transform);
            Instantiate(sans, referans.transform.position, Quaternion.Euler(0, 0, bolyaz), yazi.transform);
            Instantiate(sans, referans2.transform.position, Quaternion.Euler(0, 0, 0), yaziliste.transform);
            bolyaz = bolyaz - bol;
            rgb = rgb + 1;
        }
    }
    public void listesil()
    {
        if (sayi > 0)
        {
            sayi = sayi - 1;
            liste();
        }
    }
    public void listekle()
    {
        sayi = sayi + 1;
        liste();
    }
    public void deneme()
    {
        if (sayi > 0)
        {
            baslat.interactable = false;
            ekle.interactable = false;
            sil.interactable = false;
            StartCoroutine(wait());
        }
    }
    public IEnumerator wait()
    {
        bol = 360f / sayi;
        random = Random.Range(900, 5040);
        for (int i = 0; i < random; i = i + 10)
        {
            if (random - i > 1900)
            {
                cark.transform.Rotate(0, 0, -6);
                parcalar.transform.Rotate(0, 0, -6);
                yazi.transform.Rotate(0, 0, -6);
                yield return new WaitForSeconds(0.02f);
            }
            if (random - i <= 1900 && random - i > 900)
            {
                cark.transform.Rotate(0, 0, -2);
                parcalar.transform.Rotate(0, 0, -2);
                yazi.transform.Rotate(0, 0, -2);
                yield return new WaitForSeconds(002f);
            }
        }
        baslat.interactable = true;
        sil.interactable = true;
        for (float ii = bol; ii = 360; ii = ii + bol)
        {
            if (ilk < cark.transform.rotation.eulerAngles.z && ii > cark.transform.rotation.eulerAngles.z)
            {
                Sonuc.text = ("" + degerler[hesap] + "");
            }
            hesap = hesap + 1;
        }
    }
}