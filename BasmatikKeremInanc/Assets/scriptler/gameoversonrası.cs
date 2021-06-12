using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class gameoversonrası : MonoBehaviour
{
    public GameObject kirmizibuton;
    public GameObject mavibuton;

    public void gameover()
    {
        StartCoroutine(gameoversonrasi());
    }

    IEnumerator gameoversonrasi()
    {
        yield return new WaitForSeconds(1);
        SceneManager.LoadScene(0);
    }
}
