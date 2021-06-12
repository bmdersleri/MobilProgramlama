using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class oyunbitti : MonoBehaviour
{
    public GameObject kirmizibuton, mavibuton;

    public void OnTriggerEnter2D(Collider2D collision)
    {
               
        if (collision.gameObject.tag=="kýrmýzý")
        {       
            kirmizibuton.SetActive(false);
            mavibuton.SetActive(false);
            StartCoroutine(oyunbitirme());           
        }
    }
    IEnumerator oyunbitirme()
    {
        yield return new WaitForSeconds(1);
        SceneManager.LoadScene(3);
    }

}
