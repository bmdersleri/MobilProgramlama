#pragma warning disable 0649
using UnityEngine;
using UnityEngine.Events;

namespace Packages.HifiveInputManager.Scripts.Runtime
{
    public class SlideControl : MonoBehaviour
    {
        #region Serialized Variables

        [SerializeField] [Tooltip("You can choose slide factor to make it slow or fast")] [Range(0f, 1f)]
        private float slidingFactor = .1f;

        [SerializeField] private UnityEvent tapAction;
        [SerializeField] private UnityEvent slideAction;
        [SerializeField] private UnityEvent releaseAction;

        #endregion

        #region Private Variables

        private const int ResolutionReferenceY = 1920;
        private const int ResolutionReferenceX = 1080;

        private float resolutionFactorX = 1;
        private float resolutionFactorY = 1;

        private Vector2 touchStart = Vector2.zero;
        private Vector2 touchEnd = Vector2.zero;
        private float upDownSlide;
        private float leftRightSlide;

        #endregion

        #region Initialization

        private void Start()
        {
            resolutionFactorX = (float) ResolutionReferenceX / Screen.width;
            resolutionFactorY = (float) ResolutionReferenceY / Screen.height;

            Debug.Log("Screen width: " + Screen.width);
            Debug.Log("Screen height: " + Screen.height);
            Debug.Log("ResolutionFactorX: " + resolutionFactorX);
            Debug.Log("ResolutionFactorY: " + resolutionFactorY);
        }

        #endregion

        #region Control Loop

        private void Update()
        {
            if (Input.GetMouseButtonDown(0))
            {
                touchStart = Input.mousePosition;
                tapAction.Invoke();
            }

            if (Input.GetMouseButton(0))
            {
                touchEnd = Input.mousePosition;

                upDownSlide = (touchEnd.y - touchStart.y) * resolutionFactorY * slidingFactor;
                leftRightSlide = (touchEnd.x - touchStart.x) * resolutionFactorX * slidingFactor;
                slideAction.Invoke();
            }

            if (Input.GetMouseButtonUp(0))
            {
                releaseAction.Invoke();
            }
        }

        #endregion

        #region Public Variables

        /// <summary>
        /// Returns Up-Down slide value
        /// </summary>
        /// <returns></returns>
        public float GetUpDownSlide()
        {
            return upDownSlide;
        }

        /// <summary>
        /// Returns Left-Right slide value
        /// </summary>
        /// <returns></returns>
        public float GetLeftRightSlide()
        {
            return leftRightSlide;
        }

        #endregion

        #region Debug

        public void DebugSlide()
        {
            Debug.Log("Slide invoked!");
        }

        public void DebugRelease()
        {
            Debug.Log("Release invoked!");
        }

        public void DebugUpDownSlide()
        {
            Debug.Log(GetUpDownSlide());
        }

        public void DebugLeftRightSlide()
        {
            Debug.Log(GetLeftRightSlide());
        }

        #endregion
    }
}