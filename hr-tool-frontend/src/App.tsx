import React, { useState, useEffect } from 'react';
import './App.css';
import CandidateList from './components/CandidateList';
import CandidateForm from './components/CandidateForm';
import { JobCandidate } from './types';
import api from './api/axios';

function App() {
  const [candidates, setCandidates] = useState<JobCandidate[]>([]);
  const [searchName, setSearchName] = useState('');
  const [searchSkills, setSearchSkills] = useState('');
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadCandidates();
  }, []);

  const loadCandidates = async () => {
    setLoading(true);
    try {
      const res = await api.get('/candidates');
      setCandidates(res.data);
    } catch (err) {
      console.error('Failed to load candidates', err);
    } finally {
      setLoading(false);
    }
  };

  const onAddCandidate = async (candidate: JobCandidate) => {
    setLoading(true);
    try {
      const payload = {
        fullName: candidate.fullName,
        dateOfBirth: candidate.dateOfBirth,
        contactNumber: candidate.contactNumber,
        email: candidate.email,
        skills: candidate.skills || []
      };
      await api.post('/candidates', payload);
      loadCandidates();
    } catch (err) {
      console.error('Failed to add candidate', err);
    } finally {
      setLoading(false);
    }
  };

  const onUpdateCandidate = async (id: number, candidate: JobCandidate) => {
    setLoading(true);
    try {
      const payload = {
        fullName: candidate.fullName,
        dateOfBirth: candidate.dateOfBirth,
        contactNumber: candidate.contactNumber,
        email: candidate.email,
        skills: candidate.skills || []
      };
      await api.put(`/candidates/${id}`, payload);
      loadCandidates();
    } catch (err) {
      console.error('Failed to update candidate', err);
    } finally {
      setLoading(false);
    }
  };

  const onDeleteCandidate = async (id: number) => {
    setLoading(true);
    try {
      await api.delete(`/candidates/${id}`);
      loadCandidates();
    } catch (err) {
      console.error('Failed to delete candidate', err);
    } finally {
      setLoading(false);
    }
  };

  const onSearchByName = async () => {
    if (!searchName.trim()) {
      loadCandidates();
      return;
    }
    setLoading(true);
    try {
      const res = await api.get('/candidates/search/by-name', {
        params: { name: searchName }
      });
      setCandidates(res.data);
    } catch (err) {
      console.error('Search failed', err);
    } finally {
      setLoading(false);
    }
  };

  const onSearchBySkills = async () => {
    if (!searchSkills.trim()) {
      loadCandidates();
      return;
    }
    setLoading(true);
    try {
      const skills = searchSkills.split(',').map((s: string) => s.trim());
      const res = await api.get('/candidates/search/by-skills', {
        params: { skills }
      });
      setCandidates(res.data);
    } catch (err) {
      console.error('Search failed', err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>HR Tool</h1>
      </header>

      <div className="container">
        <div className="form-section">
          <CandidateForm onSubmit={onAddCandidate} />
        </div>

        <div className="search-section">
          <div className="search-group">
            <input
              type="text"
              placeholder="Search by name..."
              value={searchName}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) => setSearchName(e.target.value)}
            />
            <button onClick={onSearchByName}>Search</button>
          </div>
          <div className="search-group">
            <input
              type="text"
              placeholder="Skills (comma separated)..."
              value={searchSkills}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) => setSearchSkills(e.target.value)}
            />
            <button onClick={onSearchBySkills}>Search</button>
          </div>
          <button onClick={loadCandidates} className="reset-btn">Reset</button>
        </div>

        {loading && <div className="loading">Loading...</div>}

        <CandidateList
          candidates={candidates}
          onDelete={onDeleteCandidate}
          onUpdate={onUpdateCandidate}
        />
      </div>
    </div>
  );
}

export default App;
