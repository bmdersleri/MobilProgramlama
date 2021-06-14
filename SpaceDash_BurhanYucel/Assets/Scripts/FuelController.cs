using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class FuelController : MonoBehaviour
{
    private float fuelCount;
    [SerializeField]private float maxFuelCount;

    
    [SerializeField] private Image fuelFill;
    public float FuelCount
    {
        get => fuelCount;
        set
        {
            if (value <= 0)
            {
                fuelCount = 0;
                GameManager.Instance.Lose();
            }
            else
            {
                fuelCount = Mathf.Clamp(value,0,maxFuelCount);
            }
            
            fuelFill.fillAmount = (fuelCount / maxFuelCount);
        }
    }

    public void AddFuel()
    {
        FuelCount = maxFuelCount;
    }
    private void Awake()
    {
        fuelFill.fillAmount = 1;
        FuelCount = maxFuelCount;
    }

    private void FixedUpdate()
    {
        if (!GameManager.Instance.isStart) return;
        FuelCount -= Time.fixedDeltaTime*5;
    }
}
