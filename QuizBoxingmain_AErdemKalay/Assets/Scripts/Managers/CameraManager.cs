using System.Collections;
using System.Collections.Generic;
using Cinemachine;
using DG.Tweening;
using UnityEngine;

public class CameraManager : MonoSingleton<CameraManager>
{
    public CinemachineVirtualCamera boxingCamera;

    public void SetShake()
    {
        var perlin = boxingCamera.GetCinemachineComponent<CinemachineBasicMultiChannelPerlin>();
        perlin.m_AmplitudeGain = 5;
        DOVirtual.DelayedCall(0.2f, () => {
            perlin.m_AmplitudeGain = 0;
        });
    }
}
