import { useState } from 'react';
import './App.css';
import Generation from './pages/generation';
import Index from './pages/Index';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';


function App() {

  const [files, setFiles] = useState([])
  const [page, setPage] = useState(false)

  const addFile = (file) => {
    setFiles(prev => [...prev, {...file, id: prev.length}])
  }

  const generate = () => setPage(true)

  return <div className='App'>
    {page? <Generation files={files} />: <Index files={files} addFiles={addFile} setFiles={setFiles} generate={generate} />}
    <ToastContainer position='bottom-right' />
  </div>
}

export default App;
