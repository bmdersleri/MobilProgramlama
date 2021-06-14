using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;

public class HifiveShadersEditor : Editor
{
    static GameObject lowPolyWater;
    static GameObject snowtracks;

    #region Creators
    // Call to load and create LowPoly Water
    [MenuItem("Hifive Games/Shaders/Create Low Poly Water")]
    static void CreateLowPolyWater()
    {
        lowPolyWater = Resources.Load<GameObject>("WaterPlane");
        GameObject go = Instantiate(lowPolyWater);
        go.name = "Low Poly Water";
        Selection.activeGameObject = go;
        Debug.Log("<color=red><b>Hifive Shaders:</b></color> Low Poly Water Created!");
    }

    // Call to load and create Snowtracks Plane
    [MenuItem("Hifive Games/Shaders/Snowtracks/Create Snowtracks")]
    static void CreateSnowtracks()
    {
        snowtracks = Resources.Load<GameObject>("SnowtracksPlane");
        GameObject go = Instantiate(snowtracks);
        go.name = "Snowtracks";
        Selection.activeGameObject = go;
        Debug.Log("<color=red><b>Hifive Shaders:</b></color> Snowtracks Created!");
    }

    // Call to add DrawWithMovement to GameObject
    [MenuItem("Hifive Games/Shaders/Snowtracks/Add DrawWithMovement")]
    static void AddDrawWithMovement()
    {
        Selection.activeGameObject.AddComponent<Hifive.Shaders.DrawWithMovement>();
        Debug.Log("<color=red><b>Hifive Shaders:</b></color> DrawWithMovement Component Added to Game Object: " + Selection.activeGameObject.name);
    }
    #endregion

    #region Validators

    #endregion
}
