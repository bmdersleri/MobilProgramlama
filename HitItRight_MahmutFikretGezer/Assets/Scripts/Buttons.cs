using System.Threading;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Buttons : MonoBehaviour
{
    private void Start()
    {
        Time.timeScale = 0;
    }

    public void GoHome()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
    }

    public void btnPlay()
    {
        Time.timeScale = 1;
    }

    public void btnHowTo()
    {
        SceneManager.LoadScene(1);
    }

}
