using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using NaughtyAttributes;

[CreateAssetMenu(fileName = "GameData", menuName = "Scriptable Objects/Game Data", order = 2)]
public class Game : ScriptableObject
{
    //[Header("Game Settings")]

    [ReorderableList]
    public List<Level> levels = new List<Level>();
}
