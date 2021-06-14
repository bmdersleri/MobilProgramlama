using UnityEngine;

namespace Packages.HifiveUtilities.Runtime
{
    public class AspectRatioFitter : MonoBehaviour
    {
        //public float orthographicSizeOffset = 0f;
        public float multiplier;
        Camera _cam;
        float refRatio = 16f / 9f;
        float refDif = (17f / 9f) - (16f / 9f);

        // Start is called before the first frame update
        void Start()
        {
            _cam = GetComponent<Camera>();

            if (_cam.orthographic)
            {
                FitCameraForOrthographic();
            }
            else
            {
                FitCameraForPerspective();
            }
        }

        void FitCameraForOrthographic()
        {
            // Find aspect ratio
            float ratio = Screen.height / (float)Screen.width;
            float difRatio = Mathf.Abs(refRatio - ratio);
            float difference = difRatio / refDif;


            // Set size of Orthographic camera
            if (ratio > refRatio)
                _cam.orthographicSize += multiplier * difference;
            else
                _cam.orthographicSize -= multiplier * difference;
        }

        void FitCameraForPerspective()
        {
            // Find aspect ratio
            float ratio = Screen.height / (float)Screen.width;
            float difRatio = Mathf.Abs(refRatio - ratio);
            float difference = difRatio / refDif;


            // Set size of Perspeective camera
            if (ratio > refRatio)
                _cam.fieldOfView += multiplier * difference;
            else
                _cam.fieldOfView -= multiplier * difference;
        }
    }
}


