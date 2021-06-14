using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu(fileName = "Level",menuName = "New Level")]
public class LevelQuestion : ScriptableObject
{
    public string categoryTitle;
    public List<QuestionStruct> questions;
}
