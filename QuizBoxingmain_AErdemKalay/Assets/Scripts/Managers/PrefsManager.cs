using NaughtyAttributes;
using Unisave;
using UnityEngine;
using System.Collections.Generic;

public class PrefsManager : MonoBehaviour
{
    private static PrefsManager _instance = null;
    public static PrefsManager Instance => _instance;

    private void Awake()
    {
        if (_instance != null && _instance != this)
        {
            Destroy(this.gameObject);
        }

        _instance = this;

        UnisaveLocal.Load(this);
    }


    /// <summary>
    /// Saves everything while this game object destroyed
    /// </summary>
    private void OnDestroy()
    {
        UnisaveLocal.Save(this);
    }
}