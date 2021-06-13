using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class randomspawn : MonoBehaviour
{
    public GameObject[] spawnobje;
    public float x;
    public float y;
    public float sayac = 0;
    public Button btn;

    void Start()
    {
       
    }

   
    void FixedUpdate()
    {
        sayac += 0.1f;
        if (sayac>=2f)
        {
            buttonspawn();
            sayac = 0;
        }
    }
    void coinspawn()
    {
      

    }
    void buttonspawn()
    {
        x = Random.Range(-9.7f, 9.7f);
        y = Random.Range(-4.4f, 4.4f);
        Instantiate(btn, new Vector2(x, y), Quaternion.identity);
        btn = GameObject.Find("coinbutton").GetComponent<Button>();
        btn.onClick.AddListener(() => týklama());
    }
    void týklama()
    {
        Destroy(btn.gameObject);
    }
}
