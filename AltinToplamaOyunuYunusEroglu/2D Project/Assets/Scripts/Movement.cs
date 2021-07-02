using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Movement : MonoBehaviour
{
    public float hiz, ziplamaGucu,h;
    public bool yerde,android,sol, sag;
    public int altin;
    public Text AltinMiktar;
    public GameObject androidpanel;
    Rigidbody2D a;
    Animator anim;


    void Start()
    {
        anim = GetComponent<Animator>();
        a = GetComponent<Rigidbody2D>();

    }
    void FixedUpdate()
    {
        anim.SetBool("Yerde", yerde);
        anim.SetFloat("Speed", Mathf.Abs(h));
        if (android)
        {
            if(sol)
            {
                h = -1;
                transform.localScale = new Vector3(1, 1, 1);
                transform.Translate(-h * hiz * Time.deltaTime, 0, 0);
            }
            if(sag)
            {
                h = 1;
                transform.localScale = new Vector3(-1, 1, 1);
                transform.Translate(h * hiz * Time.deltaTime, 0, 0);
            }
            if(!sol && !sag)
            {
                h = 0;
            }
        }
        else
        {
            h =Input.GetAxis("Horizontal");
            

            if (h > 0.1f)
            {
                transform.localScale = new Vector3(1, 1, 1);
            }
            if (h < -0.1f)
            {
                transform.localScale = new Vector3(-1, 1, 1);
            }
            if (h > 0)
            {
                transform.Translate(h * hiz * Time.deltaTime, 0, 0);
            }
            if (h < 0)
            {
                transform.Translate(-h * hiz * Time.deltaTime, 0, 0);
            }
        }
       
    }
    void Update()
    {
        AltinMiktar.text = "" + altin;

        //if (Input.GetMouseButtonDown(0))
          //  {
            //    transform.position = Camera.main.ScreenToWorldPoint(new Vector3(Input.mousePosition.x, Input.mousePosition.y, 10));
            //}

            if(Input.GetKeyDown(KeyCode.Space) && yerde)
        {
            Ziplama();
        }
            
             if (transform.position.y < -7.1f)                        //aşağı düştüğünde ölmesi
        {
            dead();
        }
        if(android)
        {
            androidpanel.SetActive(true);
        }
        else
        {
            androidpanel.SetActive(false); 
        }
    }


    void dead()
    {
        Application.LoadLevel(Application.loadedLevel);         //öldüğündee oyun başlar.
    }
    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag == ("Coin"))
        {
            altin++;
            Destroy(other.gameObject);
        }
    }
    public void Ziplama()
    {
        a.AddForce(Vector3.up * ziplamaGucu);
    }


}

