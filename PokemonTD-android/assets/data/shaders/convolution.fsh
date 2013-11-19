#version 100
#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoord;

//declare uniforms
uniform sampler2D u_texture;
uniform float conMatrix[9];
uniform float conWeight;
uniform vec2 conPixel;


void main(void)
{
    vec4 color = vec4(0.0);
    vec2 offset = conPixel * 1.5;
    vec2 start = v_texCoord - offset;
    vec2 current = start;
    
    for (int i = 0; i < 9; i++)
    {
        color += texture2D( u_texture, current ) * conMatrix[i];
        
        current.x += conPixel.x;
        if (i == 2 || i == 5) {
            current.x = start.x;
            current.y += conPixel.y;
        }
    }
    
    gl_FragColor = color * conWeight;
}