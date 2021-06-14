using System;
using System.Collections;
using System.Collections.Generic;
using Unity.Mathematics;
using UnityEngine;
using UnityEngine.AI;
using Random = UnityEngine.Random;

public class MapGeneration : MonoBehaviour
{
    [SerializeField] private GameObject obstaclePrefab;
    [SerializeField] private GameObject fuelPrefab;
    private Vector2 screenBounds;
    

    private void Start()
    {
        screenBounds = Camera.main.ScreenToWorldPoint(new Vector3(Screen.width, Screen.height, Camera.main.transform.position.z));
    }

    public void init()
    {
        InvokeRepeating("CreateObstacle", 1, 1f);
        InvokeRepeating("CreateFuel", 2, Random.Range(2,10));
    }

    // private void Update()
    // {
    //     if (fuelPrefab.transform.position.x < screenBounds.x) ;
    //     {
    //         
    //     }
    // }

    private void CreateObstacle()
    {
        if (!GameManager.Instance.isStart) return;
        GameObject obstacle = Instantiate(obstaclePrefab, transform.position, quaternion.identity,transform);
        Vector2 position = obstacle.transform.position;
        position.x = Random.Range(-1.4f, 1.4f);
        obstacle.transform.position = position;
    }

    private void CreateFuel()
    {
        if (!GameManager.Instance.isStart) return;
        GameObject fuel = Instantiate(fuelPrefab, transform.position, quaternion.identity);
        fuel.transform.position = new Vector2(Random.Range(-screenBounds.x, screenBounds.x), screenBounds.y);
    }
    

    private void OnTriggerEnter2D(Collider2D other)
    {
        
    }
}
