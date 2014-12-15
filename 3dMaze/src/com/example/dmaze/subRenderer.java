package com.example.dmaze;

import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLU;
import com.example.dmaze.MainSurfaceView;
import com.example.dmaze.FileOperations;

/*
 * MainRenderer.
 * This is the where the all the display at.
 */
public class subRenderer implements MainSurfaceView.Renderer {

	private Context context;
	float xOffset = 0;
	float yOffset = 0;

	public subRenderer(Context context, float x, float y) {
		this.context = context;
		xOffset = x;
		yOffset = y;

	}

	// color of the light
	float[] colorBlack = { 0.0f, 0.0f, 0.0f, 1.0f };
	float[] colorWhite = { 1.0f, 1.0f, 1.0f, 1.0f };
	float[] colorGray = { 0.6f, 0.6f, 0.6f, 1.0f };
	float[] colorRed = { 1.0f, 0.25f, 0.0f, .02f };
	float[] colorBlue = { 0.0f, 0.0f, 1.0f, 1.0f };
	float[] colorBlueA = { 0.0f, 0.0f, 1.0f, 0.0f };
	float[] colorGreen = { 0.0f, 1.0f, 0.0f, 1.0f };
	float[] colorYellow = { 1.0f, 1.0f, 0.0f, 1.0f };
	float[] colorLightYellow = { .5f, .5f, 0.0f, 1.0f };
	float[] colorGold = { 0.8f, 0.498039f, 0.196078f, 1.0f };
	float[] colorOr = { 1f, 0.5f, 0f, 1.0f };

	// if I using the keyboard I can set the lighting on or off
	boolean lightingEnabled = false;

	// add the odd lighting for later time
	private float[] lightDiffuse = { 0.5f, 0.5f, 0.5f, 0.5f };
	private float[] lightPosition = { 0.0f, 0.0f, 10.0f, 0.5f };

	// make the treasure
	private final endGame end = new endGame();

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glEnable(GL10.GL_LIGHTING);

		gl.glEnable(GL10.GL_LIGHT7);

		float spotDirectio4[] = { xOffset, yOffset, -2.5f };
		gl.glLightfv(GL10.GL_LIGHT7, GL10.GL_SPOT_DIRECTION, spotDirectio4, 0);
		gl.glLightfv(GL10.GL_LIGHT7, GL10.GL_AMBIENT, colorLightYellow, 0);
		gl.glLightfv(GL10.GL_LIGHT7, GL10.GL_SPECULAR, colorWhite, 0);
		gl.glLightfv(GL10.GL_LIGHT7, GL10.GL_DIFFUSE, colorGold, 0);
		gl.glLightf(GL10.GL_LIGHT7, GL10.GL_CONSTANT_ATTENUATION, 0.2f);
		gl.glPushMatrix();
		gl.glTranslatef(xOffset, yOffset, 0.0f);

		end.draw(gl);
		gl.glPopMatrix();

		gl.glDisable(GL10.GL_TEXTURE_2D);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f,
				100.0f);
		gl.glViewport(0, 0, width, height);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glEnable(GL10.GL_TEXTURE_2D); // Enable texture (NEW)
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		end.loadTexture(gl, context);    // Load image into Texture (NEW)
	      gl.glEnable(GL10.GL_TEXTURE_2D);  // Enable texture (NEW)
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

	}

}
