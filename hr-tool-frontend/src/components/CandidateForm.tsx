import React, { useState } from 'react';
import { JobCandidate } from '../types';

interface Props {
  onSubmit: (candidate: JobCandidate) => void;
}

const CandidateForm: React.FC<Props> = ({ onSubmit }) => {
  const [form, setForm] = useState<JobCandidate>({
    fullName: '',
    dateOfBirth: '',
    contactNumber: '',
    email: '',
    skills: [],
  });

  const [skill, setSkill] = useState('');

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
  };

  const addSkill = () => {
    if (skill.trim()) {
      setForm(prev => ({
        ...prev,
        skills: [...(prev.skills || []), skill.trim()]
      }));
      setSkill('');
    }
  };

  const removeSkill = (index: number) => {
    setForm(prev => ({
      ...prev,
      skills: prev.skills?.filter((_: string, i: number) => i !== index) || []
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!form.fullName || !form.dateOfBirth || !form.contactNumber || !form.email) {
      alert('Fill all fields');
      return;
    }

    onSubmit(form);
    setForm({
      fullName: '',
      dateOfBirth: '',
      contactNumber: '',
      email: '',
      skills: [],
    });
  };

  return (
    <form onSubmit={handleSubmit} className="candidate-form">
      <h2>Add Candidate</h2>

      <div className="form-group">
        <label htmlFor="fullName">Name *</label>
        <input
          type="text"
          id="fullName"
          name="fullName"
          value={form.fullName}
          onChange={handleChange}
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="dateOfBirth">DOB *</label>
        <input
          type="date"
          id="dateOfBirth"
          name="dateOfBirth"
          value={form.dateOfBirth}
          onChange={handleChange}
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="contactNumber">Phone *</label>
        <input
          type="text"
          id="contactNumber"
          name="contactNumber"
          value={form.contactNumber}
          onChange={handleChange}
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="email">Email *</label>
        <input
          type="email"
          id="email"
          name="email"
          value={form.email}
          onChange={handleChange}
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="skill">Skills</label>
        <div className="skill-input-group">
          <input
            type="text"
            id="skill"
            value={skill}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => setSkill(e.target.value)}
            placeholder="Add skill..."
          />
          <button type="button" onClick={addSkill}>Add</button>
        </div>
        <div className="skills-list">
          {form.skills?.map((s: string, i: number) => (
            <span key={i} className="skill-tag">
              {s}
              <button type="button" onClick={() => removeSkill(i)} className="remove-skill">
                ×
              </button>
            </span>
          ))}
        </div>
      </div>

      <button type="submit" className="submit-btn">Add Candidate</button>
    </form>
  );
};

export default CandidateForm;
