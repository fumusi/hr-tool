import React, { useState } from 'react';
import { JobCandidate } from '../types';

interface Props {
  candidates: JobCandidate[];
  onDelete: (id: number) => void;
  onUpdate: (id: number, candidate: JobCandidate) => void;
}

const CandidateList: React.FC<Props> = ({ candidates, onDelete, onUpdate }: Props) => {
  const [editId, setEditId] = useState<number | null>(null);
  const [editForm, setEditForm] = useState<JobCandidate | null>(null);
  const [newSkill, setNewSkill] = useState('');

  if (!candidates.length) {
    return <div className="empty-message">No candidates</div>;
  }

  const startEdit = (c: JobCandidate) => {
    setEditId(c.id || null);
    setEditForm({ ...c });
    setNewSkill('');
  };

  const cancelEdit = () => {
    setEditId(null);
    setEditForm(null);
    setNewSkill('');
  };

  const saveEdit = () => {
    if (editForm && editId) {
      onUpdate(editId, editForm);
      cancelEdit();
    }
  };

  const addSkillToEdit = () => {
    if (editForm && newSkill.trim()) {
      const skills = editForm.skills || [];
      if (!skills.includes(newSkill.trim())) {
        setEditForm({
          ...editForm,
          skills: [...skills, newSkill.trim()]
        });
        setNewSkill('');
      }
    }
  };

  const removeSkillFromEdit = (idx: number) => {
    if (editForm) {
      setEditForm({
        ...editForm,
        skills: editForm.skills?.filter((_: string, i: number) => i !== idx) || []
      });
    }
  };

  return (
    <div className="candidate-list">
      <h2>Candidates ({candidates.length})</h2>
      <div className="candidates-grid">
        {candidates.map((c: JobCandidate) => {
          const isEditing = editId === c.id;

          if (isEditing && editForm) {
            return (
              <div key={c.id} className="candidate-card edit-mode">
                <div className="edit-form">
                  <h3>Edit {c.fullName}</h3>

                  <div className="form-field">
                    <label>Name</label>
                    <input
                      type="text"
                      value={editForm.fullName}
                      onChange={(e) => setEditForm({ ...editForm, fullName: e.target.value })}
                    />
                  </div>

                  <div className="form-field">
                    <label>DOB</label>
                    <input
                      type="date"
                      value={editForm.dateOfBirth}
                      onChange={(e) => setEditForm({ ...editForm, dateOfBirth: e.target.value })}
                    />
                  </div>

                  <div className="form-field">
                    <label>Phone</label>
                    <input
                      type="text"
                      value={editForm.contactNumber}
                      onChange={(e) => setEditForm({ ...editForm, contactNumber: e.target.value })}
                    />
                  </div>

                  <div className="form-field">
                    <label>Email</label>
                    <input
                      type="email"
                      value={editForm.email}
                      onChange={(e) => setEditForm({ ...editForm, email: e.target.value })}
                    />
                  </div>

                  <div className="form-field">
                    <label>Skills</label>
                    <div className="skill-input">
                      <input
                        type="text"
                        value={newSkill}
                        onChange={(e) => setNewSkill(e.target.value)}
                        placeholder="Add skill..."
                      />
                      <button onClick={addSkillToEdit}>Add</button>
                    </div>
                    <div className="edit-skills">
                      {editForm.skills?.map((s: string, i: number) => (
                        <span key={i} className="skill-tag">
                          {s}
                          <button onClick={() => removeSkillFromEdit(i)}>×</button>
                        </span>
                      ))}
                    </div>
                  </div>

                  <div className="edit-buttons">
                    <button className="save-btn" onClick={saveEdit}>Save</button>
                    <button className="cancel-btn" onClick={cancelEdit}>Cancel</button>
                  </div>
                </div>
              </div>
            );
          }

          return (
            <div key={c.id} className="candidate-card">
              <div className="card-header">
                <h3>{c.fullName}</h3>
                <div className="card-actions">
                  <button className="edit-btn" onClick={() => startEdit(c)}>Edit</button>
                  <button
                    className="delete-btn"
                    onClick={() => {
                      if (window.confirm(`Delete ${c.fullName}?`)) {
                        onDelete(c.id!);
                      }
                    }}
                  >
                    Delete
                  </button>
                </div>
              </div>

              <div className="card-body">
                <p><strong>DOB:</strong> {c.dateOfBirth}</p>
                <p><strong>Phone:</strong> {c.contactNumber}</p>
                <p><strong>Email:</strong> {c.email}</p>

                {c.skills && c.skills.length > 0 && (
                  <div className="skills">
                    <strong>Skills</strong>
                    <div className="skills-display">
                      {c.skills.map((skill: string, i: number) => (
                        <span key={i} className="skill-badge">{skill}</span>
                      ))}
                    </div>
                  </div>
                )}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default CandidateList;
