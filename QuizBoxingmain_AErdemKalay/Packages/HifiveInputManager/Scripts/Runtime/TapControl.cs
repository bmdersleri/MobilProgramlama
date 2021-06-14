#pragma warning disable 0649
using UnityEngine;
using UnityEngine.Events;

namespace Packages.HifiveInputManager.Scripts.Runtime
{
    public class TapControl : MonoBehaviour
    {
        #region Serialized Variables
        [SerializeField] private UnityEvent tapAction;
        [SerializeField] private UnityEvent leftTapAction;
        [SerializeField] private UnityEvent rightTapAction;
        #endregion

        private float screenWidth;

        private void Start()
        {
            screenWidth = Screen.width;
        }

        #region Control Loop

        private void Update()
        {
            if (Input.GetMouseButtonDown(0))
            {
                tapAction.Invoke();
                if(Input.mousePosition.x < screenWidth / 2)
                {
                    leftTapAction.Invoke();
                }
                else
                {
                    rightTapAction.Invoke();
                }
            }
        }
        #endregion

        #region Debug
        public void Debugger()
        {
            Debug.Log("Tap invoked!");
        }

        public void DebugLeftTap()
        {
            Debug.Log("Left Tap invoked!");
        }

        public void DebugRightTap()
        {
            Debug.Log("Right Tap invoked!");
        }
        #endregion
    }
}
