import axios from 'axios'
import React, { useState } from 'react'
import { toast } from 'react-toastify'
import FileGraph from '../components/FileGraph'
import FileTabs from '../components/FileTabs'
import './generation.css'

export default function Generation({ files }) {

    const [selectedId, setId] = useState(0)
    const [overlap, setOverlap] = useState([])

    const setSelected = id => {
        if (id < 0 || id > files.length)
            return

        if (id !== selectedId) {
            setOverlap([])

            setId(id)
        } 
    }

    function fetcher(endpoint) {
        return () => {
            axios.post('http://localhost:8080/job/'+endpoint, files[selectedId].resources)
            .then(res => setOverlap(res.data))
            .catch(e => {console.log(e); toast(e.response.data.message, { type: 'error' })})
            .catch(e => console.log(e))
        }
    }
 
    return (
        <div className='generation'>
            <FileTabs tabs={files} selected={selectedId} setSelected={setSelected} />

            <div className='button-container'>
                {files[selectedId].method === 1 || files[selectedId].method === 3? <button onClick={fetcher('all')}>Generer Parallele</button>: <></>}
                {files[selectedId].method === 2 || files[selectedId].method === 3? <button onClick={fetcher('part')}>Generer Différée</button>: <></>}
            </div>

            <FileGraph resources={files[selectedId].resources} overlap={overlap} />
        </div>
    )
}
