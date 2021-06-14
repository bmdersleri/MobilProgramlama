#pragma warning disable 0649
using UnityEngine;
using UnityEngine.Events;

namespace Packages.HifiveInputManager.Scripts.Runtime
{
    public class TapHoldControl : MonoBehaviour
    {
        #region Serialized Variables
        [SerializeField] private UnityEvent tapAction;
        [SerializeField] private UnityEvent holdAction;
        [SerializeField] private UnityEvent releaseAction;
        #endregion

        #region Control Loop

        private void Update()
        {
            if (Input.GetMouseButtonDown(0))
            {
                tapAction.Invoke();
            }
            if (Input.GetMouseButton(0))
            {
                holdAction.Invoke();
            }
            if (Input.GetMouseButtonUp(0))
            {
                releaseAction.Invoke();
            }
        }
        #endregion

        #region Debug
        public void DebugHold()
        {
            Debug.Log("Hold invoked!");
        }

        public void DebugRelease()
        {
            Debug.Log("Release invoked!");
        }
        #endregion
    }
}
