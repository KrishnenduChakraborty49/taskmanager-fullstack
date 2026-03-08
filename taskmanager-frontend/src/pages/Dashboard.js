import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Dashboard() {

  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [editingTaskId, setEditingTaskId] = useState(null);

  const navigate = useNavigate();

  const token = localStorage.getItem("token");

  const fetchTasks = async () => {

    try {

      const response = await axios.get(
        "http://localhost:8080/api/v1/tasks",
        {
          headers: {
            Authorization: "Bearer " + token
          }
        }
      );

      setTasks(response.data);

    } catch (error) {

      console.error("Error fetching tasks", error);

    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const createTask = async (e) => {

    e.preventDefault();

    try {

      await axios.post(
        "http://localhost:8080/api/v1/tasks",
        {
          title: title,
          description: description
        },
        {
          headers: {
            Authorization: "Bearer " + token
          }
        }
      );

      setTitle("");
      setDescription("");

      fetchTasks();

    } catch (error) {

      console.error("Error creating task", error);

    }
  };

  const deleteTask = async (id) => {

    try {

      await axios.delete(
        `http://localhost:8080/api/v1/tasks/${id}`,
        {
          headers: {
            Authorization: "Bearer " + token
          }
        }
      );

      fetchTasks();

    } catch (error) {

      console.error("Error deleting task", error);

    }
  };

  const editTask = (task) => {

    setTitle(task.title);
    setDescription(task.description);
    setEditingTaskId(task.id);

  };

  const updateTask = async (e) => {

    e.preventDefault();

    try {

      await axios.put(
        `http://localhost:8080/api/v1/tasks/${editingTaskId}`,
        {
          title: title,
          description: description
        },
        {
          headers: {
            Authorization: "Bearer " + token
          }
        }
      );

      setTitle("");
      setDescription("");
      setEditingTaskId(null);

      fetchTasks();

    } catch (error) {

      console.error("Error updating task", error);

    }
  };

  const logout = () => {

    localStorage.removeItem("token");

    navigate("/");

  };

  return (
    <div>

      <h2>Dashboard</h2>

      <button onClick={logout}>Logout</button>

      <hr />

      <h3>{editingTaskId ? "Update Task" : "Create Task"}</h3>

      <form onSubmit={editingTaskId ? updateTask : createTask}>

        <input
          type="text"
          placeholder="Task Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <br/><br/>

        <input
          type="text"
          placeholder="Task Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <br/><br/>

        <button type="submit">
          {editingTaskId ? "Update Task" : "Add Task"}
        </button>

      </form>

      <hr/>

      <h3>Your Tasks</h3>

      <ul>

        {tasks.map((task) => (

          <li key={task.id}>

            <b>{task.title}</b> - {task.description}

            <br/>

            <button onClick={() => editTask(task)}>Edit</button>

            <button onClick={() => deleteTask(task.id)}>Delete</button>

          </li>

        ))}

      </ul>

    </div>
  );
}

export default Dashboard;