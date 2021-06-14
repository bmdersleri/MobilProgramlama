// Upgrade NOTE: replaced 'mul(UNITY_MATRIX_MVP,*)' with 'UnityObjectToClipPos(*)'

Shader "HifiveShaders/Outline" {
	Properties{
		_Color("Main Color", Color) = (.5,.5,.5,1)
		_OutlineColor("Outline Color", Color) = (0,0,0,1)
		_Outline("Outline width", float) = .005
		_MainTex("Base (RGB)", 2D) = "white" { }
	}

		CGINCLUDE
#include "UnityCG.cginc"

		struct appdata {
		float4 vertex : POSITION;
		float3 normal : NORMAL;
	};

	struct v2f {
		float4 pos : POSITION;
		float4 color : COLOR;
	};

	uniform float _Outline;
	uniform float4 _OutlineColor;

	v2f vert(appdata v) {
		// just make a copy of incoming vertex data but scaled according to normal direction
		v2f o;
		o.pos = v.vertex;
		o.pos.xyz += v.normal.xyz *_Outline*0.01;
		o.pos = UnityObjectToClipPos(o.pos);

		o.color = _OutlineColor;
		return o;
	}
	ENDCG

		SubShader{

		// note that a vertex shader is specified here but its using the one above
		Pass{
		Name "OUTLINE"
		Tags{ "LightMode" = "Always" }
		Cull Off
		ZWrite Off
		//ZTest Always
		ColorMask RGB // alpha not used

					  // you can choose what kind of blending mode you want for the outline
					  //Blend SrcAlpha OneMinusSrcAlpha // Normal
					  //Blend One One // Additive
					  //Blend One OneMinusDstColor // Soft Additive
					  //Blend DstColor Zero // Multiplicative
					  //Blend DstColor SrcColor // 2x Multiplicative

		CGPROGRAM
#pragma vertex vert
#pragma fragment frag

		half4 frag(v2f i) :COLOR{
		return i.color;
	}
		ENDCG
	}

		Pass{

		Name "BASE"
		//ZWrite On
		//ZTest LEqual
		//Blend SrcAlpha OneMinusSrcAlpha
		Lighting Off
		SetTexture[_MainTex]{

		constantColor[_Color]

		Combine texture * constant, texture * constant
	}
	}
	}

		Fallback "Diffuse"
}