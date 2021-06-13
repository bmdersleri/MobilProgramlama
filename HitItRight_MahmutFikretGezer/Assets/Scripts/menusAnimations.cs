using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class menusAnimations : MonoBehaviour
{
    [SerializeField]
    private GameObject particleObject;

    void Update()
    {
        if(Time.timeScale!=0)
        {
            particleObject.SetActive(false);
            this.gameObject.GetComponent<Animator>().SetBool("BtS", true);
            //this.gameObject.GetComponent<Animator>().SetBool("StB", false);
            //Instantiate(this.gameObject,this.gameObject.transform.position,Quaternion.identity);
        }
        if(Time.timeScale==0)
        {
            this.gameObject.GetComponent<Animator>().SetBool("BtS", false);
            //this.gameObject.GetComponent<Animator>().SetBool("StB", true);
            particleObject.SetActive(true);
            Invoke("menuDestroy", 0.7f);
        }
    }

    private void menuDestroy()
    {
        Destroy(this.gameObject);
    }
}
