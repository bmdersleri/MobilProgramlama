#pragma warning disable 0649
using UnityEngine;
using UnityEngine.Events;

namespace Packages.HifiveInputManager.Scripts.Runtime
{
    public class TapDragControl : MonoBehaviour
    {
        #region Serialized Variables
        [SerializeField] private Transform followerObject;
        [SerializeField] private float zDistanceForCamera;
        [SerializeField] private Vector3 followerOffset;
        
        [Space]
        
        [SerializeField] private UnityEvent tapAction;
        [SerializeField] private UnityEvent dragAction;
        [SerializeField] private UnityEvent releaseAction;
        #endregion

        #region Private Variables

        private Vector2 touchStart;
        private Vector2 touchEnd;
        private Camera cam;
        
        #endregion

        #region Initialization

        private void Start()
        {
            cam = Camera.main;
        }

        #endregion
    
        #region Control Loop
        // Update is called once per frame
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

                dragAction.Invoke();

                if (followerObject != null)
                {
                    followerObject.position = followerOffset + cam.ScreenToWorldPoint(new Vector3(Input.mousePosition.x, Input.mousePosition.y, zDistanceForCamera));
                }
            }
            if (Input.GetMouseButtonUp(0))
            {
                releaseAction.Invoke();
            }
        }
        #endregion

        #region Public Functions
        /// <summary>
        /// Returns drag distance between first tap position and last drag or release position
        /// </summary>
        /// <returns></returns>
        public float GetDragDistanceOnScreen()
        {
            float distance = (touchEnd - touchStart).magnitude;
            return distance;
        }

        /// <summary>
        /// Returns drag angle between drag vector and Vector2.right or Vector2.left
        /// </summary>
        /// <returns></returns>
        public float GetDragAngle()
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
        /// Returns inversed version of drag angle between drag vector and Vector2.right or Vector2.reft
        /// </summary>
        /// <returns></returns>
        public float GetDragAngleInverse()
        {
            float angle = GetDragAngle();
            return angle + 180;
        }

        /// <summary>
        /// Returns drag angle 360 between drag vector and Vector2.zero
        /// </summary>
        /// <returns></returns>
        public float GetDragAngle360()
        {
            Vector2 dir = touchEnd - touchStart;
            float angle;
            if (touchEnd.y > touchStart.y)
            {
                angle = Vector2.Angle(dir, Vector2.right);
            }
            else
            {
                angle = Vector2.SignedAngle(dir, Vector2.right);
                angle = (angle - 360) * -1f;
            }
            return angle;
        }
        #endregion

        #region Debug
        public void DebugTap()
        {
            Debug.Log("Tap invoked!");
        }

        public void DebugDrag()
        {
            Debug.Log("Drag invoked!");
        }

        public void DebugRelease()
        {
            Debug.Log("Release invoked!");
        }

        public void DebugDragDistanceOnScreen()
        {
            Debug.Log(GetDragDistanceOnScreen());
        }

        public void DebugDragAngle()
        {
            Debug.Log(GetDragAngle());
        }

        public void DebugDragAngleInverse()
        {
            Debug.Log(GetDragAngleInverse());
        }

        public void DebugDragAngle360()
        {
            Debug.Log(GetDragAngle360());
        }
        #endregion
    }
}
