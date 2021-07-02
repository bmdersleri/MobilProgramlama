using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class KarakterAndroid : MonoBehaviour
{
    Movement Mv;
    void Start()
    {
        Mv = GetComponent<Movement>();

    }

    // Update is called once per frame
    void Update()
    {
        
    }
   public void sol()
    {
        Mv.sol = true;
    }
    public void sag()
    {
        Mv.sag = true;
    }
    public void solUp()
    {
        Mv.sol = false;
    }
    public void sagUp()
    {
        Mv.sag = false;
    }
    public void zipla()
    {
        if(Mv.yerde)
        {
            Mv.Ziplama();
        }
    }
}
