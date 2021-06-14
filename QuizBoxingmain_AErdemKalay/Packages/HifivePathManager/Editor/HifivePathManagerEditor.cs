using UnityEditor;
using UnityEngine;

namespace Packages.HifivePathManager.Editor
{
    public class HifivePathManagerEditor : UnityEditor.Editor
    {
        private static GameObject _path;

        #region Creators

        // Call to create a new Path
        [MenuItem("Hifive Games/Path Manager/Add Path Creator")]
        private static void CreatePath()
        {
            _path = Resources.Load<GameObject>("NewPath");
            GameObject go = Instantiate(_path);
            go.name = "New Path";
            DestroyImmediate(go.GetComponent<PathCreation.PathCreator>());
            go.AddComponent<PathCreation.PathCreator>();
            Selection.activeGameObject = go;
            Debug.Log("<color=red><b>Hifive Path Manager:</b></color> New Path Created!");
        }

        // Call to add a path follower to an object
        [MenuItem("Hifive Games/Path Manager/Add Path Follower")]
        private static void AddPathFollower()
        {
            if (Selection.activeGameObject != null)
            {
                Selection.activeGameObject.AddComponent<PathCreation.Examples.PathFollower>();
                Debug.Log(
                    "<color=red><b>Hifive Path Manager:</b></color> Path Follower component added to selected object: " +
                    Selection.activeGameObject.name);
            }
            else
            {
                Debug.Log(
                    "<color=red><b>Hifive Path Manager:</b></color> You have to select an object to add Path Follower component! ");
            }
        }

        // Call to add a road mesh creator to path
        [MenuItem("Hifive Games/Path Manager/Add Road Mesh Creator")]
        private static void AddRoadMeshCreator()
        {
            if (Selection.activeGameObject != null)
            {
                Selection.activeGameObject.AddComponent<PathCreation.Examples.RoadMeshCreator>();
                Debug.Log(
                    "<color=red><b>Hifive Path Manager:</b></color> RoadMeshCreator component added to selected object: " +
                    Selection.activeGameObject.name);
            }
            else
            {
                Debug.Log(
                    "<color=red><b>Hifive Path Manager:</b></color> You have to select a path to add RoadMeshCreator component! ");
            }
        }

        #endregion
    }
}