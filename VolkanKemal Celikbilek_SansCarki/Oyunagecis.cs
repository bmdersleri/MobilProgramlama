using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Oyunagecis : MonoBehaviour
{
    public void gecis()
    {
        SceneManager.LoadScene(1, LoadSceneMode.Single);
    }
}