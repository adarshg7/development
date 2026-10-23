from fastapi import FastAPI, Depends, HTTPException
from sqlalchemy.orm import Session

import models
import schemas
from database import SessionLocal, engine

# Create tables
models.Base.metadata.create_all(bind=engine)

app = FastAPI(title="Todo API with MySQL")

# DB dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

# CREATE
@app.post("/todos/", response_model=schemas.TodoResponse)
def create_todo(todo: schemas.TodoCreate, db: Session = Depends(get_db)):
    new_todo = models.Todo(title=todo.title)
    db.add(new_todo)
    db.commit()
    db.refresh(new_todo)
    return new_todo

# READ ALL
@app.get("/todos/", response_model=list[schemas.TodoResponse])
def get_all_todos(db: Session = Depends(get_db)):
    return db.query(models.Todo).all()

# READ ONE
@app.get("/todos/{todo_id}", response_model=schemas.TodoResponse)
def get_todo(todo_id: int, db: Session = Depends(get_db)):
    todo = db.query(models.Todo).filter(models.Todo.id == todo_id).first()
    if not todo:
        raise HTTPException(status_code=404, detail="Todo not found")
    return todo

# UPDATE
@app.put("/todos/{todo_id}", response_model=schemas.TodoResponse)
def update_todo(
    todo_id: int,
    todo_update: schemas.TodoUpdate,
    db: Session = Depends(get_db)
):
    todo = db.query(models.Todo).filter(models.Todo.id == todo_id).first()
    if not todo:
        raise HTTPException(status_code=404, detail="Todo not found")

    todo.title = todo_update.title
    todo.completed = todo_update.completed
    db.commit()
    db.refresh(todo)
    return todo

# DELETE
@app.delete("/todos/{todo_id}")
def delete_todo(todo_id: int, db: Session = Depends(get_db)):
    todo = db.query(models.Todo).filter(models.Todo.id == todo_id).first()
    if not todo:
        raise HTTPException(status_code=404, detail="Todo not found")

    db.delete(todo)
    db.commit()
    return {"message": "Todo deleted successfully"}

