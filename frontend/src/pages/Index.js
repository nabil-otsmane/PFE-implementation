import React from 'react'
import DropZone from '../components/DropZone';
import FileList from '../components/FileList';
import styles from './index.module.css'


export default function Index({ files, addFiles, generate, setFiles }) {

    return (
        <div className={styles.container}>
            <DropZone add={addFiles} />
            <FileList files={files} generate={generate} setFiles={setFiles} />
        </div>
    )
}
