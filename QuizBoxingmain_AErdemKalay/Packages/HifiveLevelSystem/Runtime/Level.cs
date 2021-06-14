using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using NaughtyAttributes;

[CreateAssetMenu(fileName = "LevelData", menuName = "Scriptable Objects/Level Data", order = 1)]
public class Level : ScriptableObject
{
    [Header("Level Settings")]
    public Color backgroundColor;

    [Header(" ")]

    [ReorderableList]
    public List<Stage> stages = new List<Stage>();
}
