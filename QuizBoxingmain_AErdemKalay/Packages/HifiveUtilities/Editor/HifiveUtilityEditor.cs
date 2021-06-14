using Cinemachine;
using Packages.HifiveUtilities.Runtime;
using UnityEditor;
using UnityEngine;

namespace Packages.HifiveUtilities.Editor
{
    public class HifiveUtilityEditor : UnityEditor.Editor
    {
        private static GameObject _screenCapture;

        [@MenuItem("Hifive Games/Documentation", false, 0)]
        private static void OpenDocumentation()
        {
            Application.OpenURL("https://gitlab.com/hifive-games/hifive-game-dev-framework/hifive-game-template/-/wikis/Hifive-Game-Template-Wiki");
        }

        #region Creators
        // Call to add a aspect ratio fitter to a camera
        [MenuItem("Hifive Games/Utilities/Add Aspect Ratio Fitter")]
        static void AddAspectRatioFitter()
        {
            if (Selection.activeGameObject != null && Selection.activeGameObject.GetComponent<Camera>() != null)
            {
                Selection.activeGameObject.AddComponent<AspectRatioFitter>();
                Debug.Log("<color=red><b>Hifive Path Manager:</b></color> Aspect Ratio Fitter component added to selected object: " + Selection.activeGameObject.name);
            }
            else
                Debug.Log("<color=red><b>Hifive Utilities:</b></color> You have to select a camera to add Aspect Ratio Fitter component! ");

        }
    
        // Call to add a aspect ratio fitter cinemachine to a camera
        [MenuItem("Hifive Games/Utilities/Add Aspect Ratio Fitter - Cinemachine")]
        static void AddAspectRatioFitterCinemachine()
        {
            if (Selection.activeGameObject != null && Selection.activeGameObject.GetComponent<Camera>() != null)
            {
                Selection.activeGameObject.AddComponent<AspectRatioFitterCinemachine>();
                Debug.Log("<color=red><b>Hifive Path Manager:</b></color> Aspect Ratio Fitter component added to selected object: " + Selection.activeGameObject.name);
            }
            else
                Debug.Log("<color=red><b>Hifive Utilities:</b></color> You have to select a camera to add Aspect Ratio Fitter component! ");

        }

        // Call to adjust pivot of Selection.activeGameObject
        [MenuItem("Hifive Games/Utilities/Adjust Pivot")]
        static void AdjustPivot()
        {
            if (Selection.activeObject != null)
            {
                Tools.pivotMode = PivotMode.Center;
                var center = Tools.handlePosition;
                Tools.pivotMode = PivotMode.Pivot;
                var delta = center - Tools.handlePosition;

                if (Selection.activeGameObject.transform.childCount == 0)
                {
                    GameObject go = new GameObject();
                    Tools.pivotMode = PivotMode.Pivot;
                    go.transform.position = Tools.handlePosition;
                    Selection.activeGameObject.transform.position -= delta;
                    Selection.activeGameObject.transform.parent = go.transform;
                    go.name = Selection.activeGameObject.name;
                    Selection.activeGameObject = go;
                }
                else
                {

                    for (int i = 0; i < Selection.activeGameObject.transform.childCount; i++)
                    {
                        Selection.activeGameObject.transform.GetChild(i).gameObject.transform.position -= delta;
                    }
                }
            }
        }

        // Call to add a screen capture prefab to scene
        [MenuItem("Hifive Games/Utilities/Add ScreenCapture Tool")]
        static void AddScreenCaptureTool()
        {
            if (FindObjectOfType(typeof(ScreenCapture)) == null)
            {
                _screenCapture = Resources.Load<GameObject>("ScreenCapturePrefab");
                _screenCapture = Instantiate(_screenCapture);
                _screenCapture.name = "Screen Capture Tool";
            
                Selection.activeGameObject = _screenCapture;
            }

            Debug.Log("<color=red><b>Hifive Utilities:</b></color> Screen Capture Tool Initialized!");
        }


        #endregion

        #region Validators
        // Check selected game object have AspectRatioFitter or not
        [MenuItem("Hifive Games/Utilities/Add Aspect Ratio Fitter", true)]
        static bool ValidateAddAspectRatioFitter()
        {
            if (Selection.activeGameObject != null && !Selection.activeGameObject.GetComponent<CinemachineBrain>())
                return Selection.activeGameObject.GetComponent<AspectRatioFitter>() == null;
            else
                return false;
        }
    
        // Check selected game object have AspectRatioFitter Cinemachine or not
        [MenuItem("Hifive Games/Utilities/Add Aspect Ratio Fitter - Cinemachine", true)]
        static bool ValidateAddAspectRatioFitterCinemachine()
        {
            if (Selection.activeGameObject != null && Selection.activeGameObject.GetComponent<CinemachineBrain>())
                return Selection.activeGameObject.GetComponent<AspectRatioFitterCinemachine>() == null;
            else
                return false;
        }

        // Check any game object selected to adjust pivot
        [MenuItem("Hifive Games/Utilities/Adjust Pivot", true)]
        static bool ValidateAdjustPivot()
        {
            if (Selection.activeGameObject != null)
            {
                return true;
            }
            else
                return false;
        }
    
        // Validate CreateUICreator to check is it exists or not in hierarchy
        [MenuItem("Hifive Games/Utilities/Add Screen Capture Tool", true)]
        static bool ValidateAddScreenCaptureTool()
        {
            return FindObjectOfType(typeof(ScreenCapture)) == null;
        }

        #endregion
    }
}