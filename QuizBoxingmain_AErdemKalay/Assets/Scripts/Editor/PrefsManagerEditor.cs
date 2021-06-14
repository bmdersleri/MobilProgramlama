using UnityEditor;
using UnityEngine;
using NaughtyAttributes.Editor;

[CustomEditor(typeof(PrefsManager))]
public class PrefsManagerEditor : NaughtyInspector
{
#if UNITY_EDITOR
    
    
    public override void OnInspectorGUI()
    {
        base.OnInspectorGUI();

        if (GUILayout.Button("Open Prefs Editor"))
        {
            PrefsEditorWindow window = EditorWindow.GetWindow<PrefsEditorWindow>();
            window.titleContent = new GUIContent("Hifive Prefs Editor");
            window.minSize = new Vector2(250, 250);
            window.Show();
        }
    }
    
#endif
}
