using System.Collections;
using System.Collections.Generic;
using UnityEngine;

namespace Hifive.Shaders
{

    public class DrawWithMovement : MonoBehaviour
    {
        public GameObject target;
        public Shader _drawShader;
        public Transform _ray;
        [Range(1, 500)]
        public float _brushSize;
        [Range(0f, 1f)]
        public float _brushStrength;

        private RenderTexture _splatMap;
        private Material _snowMaterial, _drawMaterial;

        private RaycastHit _hit;

        // Start is called before the first frame update
        void Start()
        {
            _drawMaterial = new Material(_drawShader);
            _drawMaterial.SetVector("_Color", Color.red);

            _snowMaterial = target.GetComponent<MeshRenderer>().material;
            _splatMap = new RenderTexture(1024, 1024, 0, RenderTextureFormat.ARGBFloat);
            _snowMaterial.SetTexture("_Splat", _splatMap);
        }

        // Update is called once per frame
        void Update()
        {

            if (Physics.Raycast(_ray.position, Vector3.down, out _hit))
            {
                _drawMaterial.SetVector("_Coordinate", new Vector4(_hit.textureCoord.x, _hit.textureCoord.y, 0, 0));
                _drawMaterial.SetFloat("_Strength", _brushStrength);
                _drawMaterial.SetFloat("_Size", _brushSize);
                RenderTexture temp = RenderTexture.GetTemporary(_splatMap.width, _splatMap.height, 0, RenderTextureFormat.ARGBFloat);
                Graphics.Blit(_splatMap, temp);
                Graphics.Blit(temp, _splatMap, _drawMaterial);
                RenderTexture.ReleaseTemporary(temp);
            }
        }

        /*private void OnGUI()
        {
            GUI.DrawTexture(new Rect(0, 0, 256, 256), _splatMap, ScaleMode.ScaleToFit, false, 1);
        }*/

        public void UpdateBrushSize(float size)
        {
            _brushSize = size;
        }
    }

}
