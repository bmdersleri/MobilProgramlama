#pragma warning disable 0649
using UnityEngine;
using UnityEngine.Events;

namespace Packages.HifiveInputManager.Scripts.Runtime
{
    public class DoubleTapControl : MonoBehaviour
    {
        #region Serialized Variables
        [SerializeField]
        [Tooltip("You can choose delay between two tap")]
        [Range(0f, 1f)]
        float doubleTapDelay = 0.25f;

        [SerializeField] private UnityEvent doubleTapAction;
        #endregion

        #region Private Variables
        private bool isFirstTap;
        private float tempDelay;
        #endregion

        #region Initialize
        void Start()
        {
            tempDelay = doubleTapDelay;
        }
        #endregion

        #region Control Loop
        void Update()
        {
            if (tempDelay < 0f)
            {
                isFirstTap = false;
                tempDelay = doubleTapDelay;
            }

            if (Input.GetMouseButtonDown(0))
            {
                if (!isFirstTap)
                {
                    isFirstTap = true;
                }
                else
                {
                    doubleTapAction.Invoke();
                }
            }

            if (isFirstTap)
            {
                tempDelay -= Time.deltaTime;
            }
        }
        #endregion
    }
}
