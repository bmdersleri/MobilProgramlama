using Packages.HifiveInputManager.Scripts.Runtime;
using UnityEditor;
using UnityEngine;

namespace Packages.HifiveInputManager.Scripts.Editor
{
    public class HifiveInputManagerEditor : UnityEditor.Editor
    {
        static GameObject _tapControl;
        static GameObject _doubleTapControl;
        static GameObject _swipeControl;
        static GameObject _slideControl;
        static GameObject _tapDragControl;
        static GameObject _tapHoldControl;

        #region Creators

        // Call to load and create TapControl
        //[@MenuItem("Hifive Games/Input Manager", false, 22)]
        [MenuItem("Hifive Games/Inputs/Create Tap Control", false, 1)]
        static void CreateTapControl()
        {
            _tapControl = Resources.Load<GameObject>("TapControl");
            GameObject go = Instantiate(_tapControl);
            go.name = "Tap Control";
            Selection.activeGameObject = go;
            Debug.Log("<color=red><b>Hifive Input Manager:</b></color> Tap Control Created!");
        }

        // Call to load and create DoubleTapControl
        [MenuItem("Hifive Games/Inputs/Create Double Tap Control", false, 1)]
        static void CreateDoubleTapControl()
        {
            _doubleTapControl = Resources.Load<GameObject>("DoubleTapControl");
            GameObject go = Instantiate(_doubleTapControl);
            go.name = "Double Tap Control";
            Selection.activeGameObject = go;
            Debug.Log("<color=red><b>Hifive Input Manager:</b></color> Double Tap Control Created!");
        }

        // Call to load and create SwipeControl
        [MenuItem("Hifive Games/Inputs/Create Swipe Control", false, 1)]
        static void CreateSwipeControl()
        {
            _swipeControl = Resources.Load<GameObject>("SwipeControl");
            GameObject go = Instantiate(_swipeControl);
            go.name = "Swipe Control";
            Selection.activeGameObject = go;
            Debug.Log("<color=red><b>Hifive Input Manager:</b></color> Swipe Control Created!");
        }

        // Call to load and create SlideControl
        [MenuItem("Hifive Games/Inputs/Create Slide Control", false, 1)]
        static void CreateSlideControl()
        {
            _slideControl = Resources.Load<GameObject>("SlideControl");
            GameObject go = Instantiate(_slideControl);
            go.name = "Slide Control";
            Selection.activeGameObject = go;
            Debug.Log("<color=red><b>Hifive Input Manager:</b></color> Slide Control Created!");
        }

        // Call to load and create TapDragControl
        [MenuItem("Hifive Games/Inputs/Create Tap-Drag Control", false, 1)]
        static void CreateTapDragControl()
        {
            _tapDragControl = Resources.Load<GameObject>("TapDragControl");
            GameObject go = Instantiate(_tapDragControl);
            go.name = "Tap-Drag Control";
            Selection.activeGameObject = go;
            Debug.Log("<color=red><b>Hifive Input Manager:</b></color> Tap & Drag Control Created!");
        }

        // Call to load and create TapHoldControl
        [MenuItem("Hifive Games/Inputs/Create Tap-Hold Control", false, 1)]
        static void CreateTapHoldControl()
        {
            _tapHoldControl = Resources.Load<GameObject>("TapHoldControl");
            GameObject go = Instantiate(_tapHoldControl);
            go.name = "Tap-Hold Control";
            Selection.activeGameObject = go;
            Debug.Log("<color=red><b>Hifive Input Manager:</b></color> Tap & Hold Control Created!");
        }
    
        #endregion

        #region Validators
        // Validate CreateTapControl to check is it exists or not in hierarchy
        [MenuItem("Hifive Games/Inputs/Create Tap Control", true)]
        static bool ValidateCreateTapControl()
        {
            return FindObjectOfType(typeof(TapControl)) == null;
        }

        // Validate CreateDoubleTapControl to check is it exists or not in hierarchy
        [MenuItem("Hifive Games/Inputs/Create Double Tap Control", true)]
        static bool ValidateCreateDoubleTapControl()
        {
            return FindObjectOfType(typeof(DoubleTapControl)) == null;
        }

        // Validate CreateSwipeControl to check is it exists or not in hierarchy
        [MenuItem("Hifive Games/Inputs/Create Swipe Control", true)]
        static bool ValidateCreateSwipeControl()
        {
            return FindObjectOfType(typeof(SwipeControl)) == null;
        }

        // Validate CreateSlideControl to check is it exists or not in hierarchy
        [MenuItem("Hifive Games/Inputs/Create Slide Control", true)]
        static bool ValidateCreateSlideControl()
        {
            return FindObjectOfType(typeof(SlideControl)) == null;
        }

        // Validate CreateTapDragControl to check is it exists or not in hierarchy
        [MenuItem("Hifive Games/Inputs/Create Tap-Drag Control", true)]
        static bool ValidateCreateTapDragControl()
        {
            return FindObjectOfType(typeof(TapDragControl)) == null;
        }

        // Validate CreateTapHoldControl to check is it exists or not in hierarchy
        [MenuItem("Hifive Games/Inputs/Create Tap-Hold Control", true)]
        static bool ValidateCreateTapHoldControl()
        {
            return FindObjectOfType(typeof(TapHoldControl)) == null;
        }
        #endregion
    }
}
