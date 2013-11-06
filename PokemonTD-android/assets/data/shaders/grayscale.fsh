#ifdef GL_ES
precision mediump float;
#endif
uniform sampler2D u_texture;
uniform float u_beginningAngle;
uniform float u_endingAngle;

varying vec4 v_color;
varying vec2 v_texCoord;

vec4 desaturate( vec4 color){
    vec4 scaledColor = color * vec4(0.3, 0.59, 0.11, 1.0);
    float luminance = (scaledColor.r + scaledColor.g + scaledColor.b)/2.0 ;
    return vec4( luminance, luminance, luminance, color.a);
}

void main( void ) {
gl_FragColor = desaturate(texture2D(u_texture, v_texCoord));
}