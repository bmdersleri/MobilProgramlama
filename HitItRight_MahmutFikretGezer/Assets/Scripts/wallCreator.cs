using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class wallCreator : MonoBehaviour
{   
    [SerializeField]
    private GameObject wall_Block;
    [SerializeField]
    private GameObject wall_Other;
    [SerializeField]
    private Sprite[] wallSprites;
    void Awake()
    {
        CreateWalls();
    }
    
    public void CreateWalls()
    {
        //float y = -4.61f; //ilk wall'u yerleştirken base alınacak y değeri
        //float x = -2.20f; //ilk wall'u yerleştirken base alınacak x değeri
        ////Köşelerdeki kahverengi blockları kopyala
        //Instantiate(wall_Block, new Vector2(x, -4.62f), Quaternion.identity);
        //Instantiate(wall_Block, new Vector2(x, 3.44f), Quaternion.identity);
        //Instantiate(wall_Block, new Vector2(-x, -4.62f), Quaternion.identity);
        //Instantiate(wall_Block, new Vector2(-x, 3.44f), Quaternion.identity);

        float y = -4.62f; //ilk wall'u yerleştirken base alınacak y değeri
        float x = -2.20f; //ilk wall'u yerleştirken base alınacak x değeri
        for (int i = 0; i < 4; i++)
        {
            Debug.Log("x:" + x.ToString() + " y:" + y.ToString());
            Instantiate(wall_Block, new Vector2(x, y), Quaternion.identity);
            
            if(i%2==0)
            y = 3.44f;
            else
            y = -4.62f;
            if (i == 1)
                continue;
            x *= -1;          
        }

        y = -4.62f;
        x = -2.20f;

        for (int i = 0; i < 10; i++)
        {
            y = y + 0.732f;
            wall_Other.GetComponent<SpriteRenderer>().sprite = wallSprites[Random.Range(0, wallSprites.Length)]; // Wall objelerinin sprite'ını değiştirerek renk ataması yapıyor.
            Instantiate(wall_Other, new Vector2(x, y), Quaternion.identity);
        }
        y = 3.44f;
        for (int i = 0; i < 5; i++)
        {         
            x = x + 0.732f;
            wall_Other.GetComponent<SpriteRenderer>().sprite = wallSprites[Random.Range(0, wallSprites.Length)];
            Instantiate(wall_Other, new Vector2(x,y), Quaternion.identity);
        }
        x = 2.2f;
        for (int i = 0; i < 10; i++)
        {
            y = y - 0.732f;
            wall_Other.GetComponent<SpriteRenderer>().sprite = wallSprites[Random.Range(0, wallSprites.Length)];
            Instantiate(wall_Other,new Vector2(x,y),Quaternion.identity);
        }

        y = -4.62f;
        x = -2.20f; //Görüntünün daha düzgün olması için x değerini tekrar değiştirdim. Çünkü bir değeri artırırken ve çıkartırkenki değerler birbirine eşit olmuyor,
        //karşılıklı kenarlara bakarak anlayabilirsin.
        for (int i = 0; i < 5; i++)
        {
            x = x + 0.732f;
            wall_Other.GetComponent<SpriteRenderer>().sprite = wallSprites[Random.Range(0, wallSprites.Length)];
            Instantiate(wall_Other, new Vector2(x, y), Quaternion.identity);
        }

    }
}
