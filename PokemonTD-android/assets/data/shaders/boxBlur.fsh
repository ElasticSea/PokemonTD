  varying vec4 v_color;
  varying vec2 v_texCoord;

  uniform sampler2D u_texture;

  void main() {

  	vec4 sum = vec4(0.0);
  	vec2 tc = v_texCoord;
    float step = 1.0;
    sum += texture2D(u_texture, vec2(tc.x - step, tc.y - step));
    sum += texture2D(u_texture, vec2(tc.x + step, tc.y + step));
  //  sum += texture2D(u_texture, vec2(tc.x + step, tc.y - step));
 //   sum += texture2D(u_texture, vec2(tc.x - step, tc.y + step));
    //sum += texture2D(u_texture, vec2(tc.x, tc.y));
    gl_FragColor = sum / 2.0;
    }