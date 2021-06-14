using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class spawner : MonoBehaviour
{
    public monk monkeyScript;
    public GameObject agac;
    public float height;
    public float time;
    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(Spawnobject(time));
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    public IEnumerator Spawnobject(float time)
    {
        while(!monkeyScript.ölüm)
        {
            
            Instantiate(agac,new Vector3 (3, Random.Range(-height, height), 0),Quaternion.identity);
            
            yield return new WaitForSeconds(time);
        }
        
    }
}
