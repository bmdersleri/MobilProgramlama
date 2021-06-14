using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;

public class LevelSystemEditor : Editor
{

    #region Creators
    //[@MenuItem("Hifive Games/Level System", false, 11)]
    [MenuItem("Hifive Games/Level System/Create Game Data", false)]
    static void CreateGameData()
    {
        Game asset = ScriptableObject.CreateInstance<Game>();

        int rand = Random.Range(0, 99999);
        AssetDatabase.CreateAsset(asset, "Assets/GameData" + rand + ".asset");
        AssetDatabase.SaveAssets();

        EditorUtility.FocusProjectWindow();
        Selection.activeObject = asset;

        Debug.Log("<color=red><b>Hifive Level System:</b></color> Game Data created! Change it for your needs and give it a name.");
    }

    [MenuItem("Hifive Games/Level System/Create Level Data", false)]
    static void CreateLevelData()
    {
        Level asset = ScriptableObject.CreateInstance<Level>();

        int rand = Random.Range(0, 99999);
        AssetDatabase.CreateAsset(asset, "Assets/LevelData" + rand + ".asset");
        AssetDatabase.SaveAssets();

        EditorUtility.FocusProjectWindow();
        Selection.activeObject = asset;

        Debug.Log("<color=red><b>Hifive Level System:</b></color> Level Data created! Change it for your needs and give it a name.");
    }

    [MenuItem("Hifive Games/Level System/Create Stage Data", false)]
    static void CreateStageData()
    {
        Stage asset = ScriptableObject.CreateInstance<Stage>();

        int rand = Random.Range(0, 99999);
        AssetDatabase.CreateAsset(asset, "Assets/StageData" + rand + ".asset");
        AssetDatabase.SaveAssets();

        EditorUtility.FocusProjectWindow();
        Selection.activeObject = asset;

        Debug.Log("<color=red><b>Hifive Level System:</b></color> Stage Data created! Change it for your needs and give it a name.");
    }

    #endregion

}
