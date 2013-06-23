#version 120

uniform float resolution;
uniform float mouseX;

//this is our RGB color, i.e. pure red
vec3 color = vec3(1.0, 0.0, 0.0);

void main() {
    //returns our colour, as defined above
    gl_FragColor = vec4((mouseX / resolution) * 0.8, (mouseX / resolution) * 0.9, (mouseX / resolution) * 0.5, 1.0);
}