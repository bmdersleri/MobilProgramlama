using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AudioManager : MonoBehaviour
{
    public AudioClip explosionClip;
    public AudioSource explosion;
    
    public static AudioManager Instance { get; private set; }
   
    void Start()
    {
        Instance = this;
       explosion.clip = explosionClip;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
