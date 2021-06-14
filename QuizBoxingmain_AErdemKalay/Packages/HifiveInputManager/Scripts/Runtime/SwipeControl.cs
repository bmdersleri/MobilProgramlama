#pragma warning disable 0649
using UnityEngine;
using UnityEngine.Events;

namespace Packages.HifiveInputManager.Scripts.Runtime
{
    public class SwipeControl : MonoBehaviour
    {

        #region Serialized Variables
        [SerializeField]
        [Tooltip("You can choose delay to understand is it swipe or drag")]
        [Range(0f, 2f)]
        private float swipeDelay = 0.5f;

        [SerializeField]
        [Tooltip("You can choose minimum swipe drag distance to accept or reject swipe")]
        [Range(0f, 5f)]
        private float minSwipeDistance = 1f;

        [SerializeField] private UnityEvent swipeAction;
        [SerializeField] private UnityEvent swipeLeft;
        [SerializeField] private UnityEvent swipeRight;
        [SerializeField] private UnityEvent swipeUp;
        [SerializeField] private UnityEvent swipeDown;
        #endregion

        #region Private Variables

        private Vector2 touchStart;
        private Vector2 touchEnd;
        private float tempDelay;
        private bool isSwiping;
        
        #endregion

        #region Control Loop

        private void Update()
        {
            if (Input.GetMouseButtonDown(0))
            {
                touchStart = Input.mousePosition;
                tempDelay = swipeDelay;
            }
            if (Input.GetMouseButton(0))
            {
                touchEnd = Input.mousePosition;
                isSwiping = true;
                tempDelay -= Time.deltaTime;
            }
            if (Input.GetMouseButtonUp(0))
            {
                float distance = (touchEnd - touchStart).magnitude;
                if(tempDelay >= 0 && isSwiping && distance >= minSwipeDistance)
                {
                    swipeAction.Invoke();

                    if(touchEnd.y > touchStart.y)
                    {
                        if(Mathf.Abs((touchEnd.y - touchStart.y)) > Mathf.Abs((touchEnd.x - touchStart.x)))
                        {
                            swipeUp.Invoke();
                        }
                    }
                    else
                    {
                        if (Mathf.Abs((touchEnd.y - touchStart.y)) > Mathf.Abs((touchEnd.x - touchStart.x)))
                        {
                            swipeDown.Invoke();
                        }
                    }
                    if(touchEnd.x > touchStart.x)
                    {
                        if (Mathf.Abs((touchEnd.x - touchStart.x)) > Mathf.Abs((touchEnd.y - touchStart.y)))
                        {
                            swipeRight.Invoke();
                        }
                    }
                    else
                    {
                        if (Mathf.Abs((touchEnd.x - touchStart.x)) > Mathf.Abs((touchEnd.y - touchStart.y)))
                        {
                            swipeLeft.Invoke();
                        }
                    }

                    isSwiping = false;
                }
            }
        }
        #endregion

        #region Public Functions
        /// <summary>
        /// Returns swipe distance between first tap position and last swipe or release position
        /// </summary>
        /// <returns></returns>
        public float GetSwipeDistanceOnScreen()
        {
            float distance = (touchEnd - touchStart).magnitude;
            return distance;
        }

        /// <summary>
        /// Returns swipe angle between swipe vector and Vector2.right or Vector2.left
        /// </summary>
        /// <returns></returns>
        public float GetSwipeAngle()
        {
            Vector2 dir = touchEnd - touchStart;
            float angle;
            if (touchEnd.y > touchStart.y)
            {
                angle = Vector2.Angle(dir, Vector2.right);
            }
            else
            {
                angle = Vector2.Angle(dir, Vector2.left);
            }
            return angle;
        }

        /// <summary>
        /// Returns inversed version of swipe angle between swipe vector and Vector2.right or Vector2.reft
        /// </summary>
        /// <returns></returns>
        public float GetSwipeAngleInverse()
        {
            float angle = GetSwipeAngle();
            return angle + 180;
        }
        #endregion

        #region Debug
        public void DebugSwipe()
        {
            Debug.Log("Swipe invoked!");
        }

        public void DebugSwipeUp()
        {
            Debug.Log("SwipeUp invoked!");
        }

        public void DebugSwipeDown()
        {
            Debug.Log("SwipeDown invoked!");
        }

        public void DebugSwipeRight()
        {
            Debug.Log("SwipeRight invoked!");
        }

        public void DebugSwipeLeft()
        {
            Debug.Log("SwipeLeft invoked!");
        }

        public void DebugSwipeAngle()
        {
            Debug.Log(GetSwipeAngle());
        }

        public void DebugSwipeAngleInverse()
        {
            Debug.Log(GetSwipeAngleInverse());
        }

        public void DebugSwipeDistance()
        {
            Debug.Log(GetSwipeDistanceOnScreen());
        }
        #endregion
    }
}
