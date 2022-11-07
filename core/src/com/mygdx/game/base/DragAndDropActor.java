package com.mygdx.game.base;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class DragAndDropActor extends BaseActor {
  private DragAndDropActor self;
  private DropTargetActor dropTarget;
  private float grabOffsetX;
  private float grabOffsetY;
  private boolean draggable;
  private float startPositionX;
  private float startPositionY;

  public DragAndDropActor(float x, float y, Stage s) {
    super(x, y, s);
    self = this;
    draggable = true;
    addListener(new InputListener() {
      public boolean touchDown(InputEvent event, float offsetX, float offsetY, int pointer, int button) {
        if (!isDraggable()) return false;
        self.grabOffsetX = offsetX;
        self.grabOffsetY = offsetY;
        self.startPositionX = self.getX();
        self.startPositionY = self.getY();
        self.toFront();
        self.addAction(Actions.scaleTo(1.1f, 1.1f, 0.25f));
        self.onDragStart();
        return true;
      }

      public void touchDragged(InputEvent event, float offsetX, float offsetY, int pointer) {
        float deltaX = offsetX - self.grabOffsetX;
        float deltaY = offsetY - self.grabOffsetY;
        self.moveBy(deltaX, deltaY);
      }

      public void touchUp(InputEvent event, float offsetX, float offsetY, int pointer, int button) {
        self.setDropTarget(null);

        // keep track of distance to closest object
        float closestDistance = Float.MAX_VALUE;

        for (BaseActor actor : BaseActor.getList(self.getStage(), DropTargetActor.class.getCanonicalName())) {
          var target = (DropTargetActor) actor;
          if (target.isTargetable() && self.overlaps(target)) {
            float currentDistance = Vector2.dst(self.getX(), self.getY(), target.getX(), target.getY());
            // check if this target is even closer
            if (currentDistance < closestDistance) {
              self.setDropTarget(target);
              closestDistance = currentDistance;
            }
          }
        }
        self.addAction(Actions.scaleTo(1.00f, 1.00f, 0.25f));
        self.onDrop();
      }
    });
  }

  public boolean hasDropTarget() {
    return dropTarget != null;
  }

  public void setDraggable(boolean d) {
    draggable = d;
  }

  public boolean isDraggable() {
    return draggable;
  }

  public void setDropTarget(DropTargetActor dt) {
    dropTarget = dt;
  }

  public DropTargetActor getDropTarget() {
    return dropTarget;
  }

  public DragAndDropActor(Stage s) {
    this(0, 0, s);
  }

  public void moveToActor(BaseActor other) {
    float x = other.getX() + (other.getWidth() - this.getWidth()) / 2;
    float y = other.getY() + (other.getHeight() - this.getHeight()) / 2;
    addAction(Actions.moveTo(x, y, 0.50f, Interpolation.pow3));
  }

  public void moveToStart() {
    addAction(Actions.moveTo(startPositionX, startPositionY, 0.50f, Interpolation.pow3));
  }

  public void onDragStart() {
  }

  public void onDrop() {
  }
}
