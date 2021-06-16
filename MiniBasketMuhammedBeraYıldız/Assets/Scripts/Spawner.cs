using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawner : MonoBehaviour
{
    public Ball topScript;

    public GameObject Pota;

    public float maxHeight;
    public float minHeight;


    public float time;

    private void Start()
    {
        StartCoroutine(SpawnObject(time));
    }

    public IEnumerator SpawnObject(float time)
    {
        while (!topScript.bitti)
        {
            Instantiate(Pota, new Vector3(5, Random.Range(minHeight,maxHeight), 0), Quaternion.identity);
            yield return new WaitForSeconds(time);            
        }
        
    }
}
