using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using NaughtyAttributes;

[CreateAssetMenu(fileName = "StageData", menuName = "Scriptable Objects/Stage Data", order = 0)]
public class Stage : ScriptableObject
{
    [Header("Stage Settings")]
    public int stageNumber;

    [Header("Stage Contents")]
    public GameObject cutterPrefab;
    public GameObject targetObjectPrefab;
    public GameObject targetObjectFinalPrefab;
    public Color targetObjectColor;
    public Color targetObjectOutlineColor;

    [ReorderableList]
    public List<GameObject> pathToFollow = new List<GameObject>();

}
