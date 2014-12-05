package com.example.dmaze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

/*
 * body.
 * this make the made of the body of the cross.
 */
class body {
	float[] colorBlack = { 0.0f, 0.0f, 0.0f, 1.0f };
	float[] colorWhite = { 1.0f, 1.0f, 1.0f, 1.0f };
	float[] colorGray = { 0.6f, 0.6f, 0.6f, 1.0f };
	float[] colorRed = { 1.0f, 0.0f, 0.0f, 1.00f };
	float[] colorBlue = { 0.0f, 0.0f, 0.1f, 1.0f };
	float[] colorYellow = { 1.0f, 1.0f, 0.0f, 1.0f };
	float[] colorLightYellow = { .5f, .5f, 0.0f, 1.0f };
	private FloatBuffer vertexBuffer;
	private FloatBuffer texBuffer; // Buffer for texture-coords-array (NEW)

	private float[][] colors = { { 1.0f, 0.0f, 0.0f, 1.0f },
			{ 1.0f, 0.0f, 0.0f, 1.0f }, { 1.0f, 0.0f, 0.0f, 1.0f },
			{ 1.0f, 0.0f, 0.0f, 1.0f }, { 1.0f, 0.0f, 0.0f, 1.0f },
			{ 1.0f, 0.0f, 0.0f, 1.0f } };

	float[] texCoords = { // Texture coords for the above face (NEW)
	0.0f, 1.0f, // A. left-bottom (NEW)
			1.0f, 1.0f, // B. right-bottom (NEW)
			0.0f, 0.0f, // C. left-top (NEW)
			1.0f, 0.0f // D. right-top (NEW)
	};
	int[] textureIDs = new int[1]; // Array for 1 texture-ID (NEW)

	// Constructor - Set up the buffers
	public body() {

		float[] vertices = { // Vertices for the front face
		-0.5f, -1, 0.5f, // 0. left-bottom-front
				0.5f, -1, 0.5f, // 1. right-bottom-front
				-0.5f, 4, 0.5f, // 2. left-top-front
				0.5f, 4, 0.5f // 3. right-top-front
		};

		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);

		// Setup texture-coords-array buffer, in float. An float has 4 bytes
		// (NEW)
		ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4);
		tbb.order(ByteOrder.nativeOrder());
		texBuffer = tbb.asFloatBuffer();
		texBuffer.put(texCoords);
		texBuffer.position(0);
	}

	// Draw the color cube
	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CCW); // Front face in counter-clockwise
										// orientation
		gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face
		gl.glCullFace(GL10.GL_BACK); // Cull the back face (don't display)

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY); // Enable
																// texture-coords-array
																// (NEW)
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texBuffer); // Define
																// texture-coords
																// buffer (NEW)

		// Front
		gl.glColor4f(colors[0][0], colors[0][1], colors[0][2], colors[0][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Right - Rotate 90 degree about y-axis
		gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		gl.glColor4f(colors[1][0], colors[1][1], colors[1][2], colors[1][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Back - Rotate another 90 degree about y-axis
		gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		gl.glColor4f(colors[2][0], colors[2][1], colors[2][2], colors[2][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Left - Rotate another 90 degree about y-axis
		gl.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
		gl.glColor4f(colors[3][0], colors[3][1], colors[3][2], colors[3][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Bottom - Rotate 90 degree about x-axis
		gl.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
		gl.glColor4f(colors[4][0], colors[4][1], colors[4][2], colors[4][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		// Top - Rotate another 180 degree about x-axis
		gl.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
		gl.glColor4f(colors[5][0], colors[5][1], colors[5][2], colors[5][3]);
		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);

	}

}