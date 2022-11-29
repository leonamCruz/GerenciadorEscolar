package br.com.leonamCruz.control.serviceDao;

import br.com.leonamCruz.control.serviceEntidade.ServiceAluno;
import br.com.leonamCruz.model.dao.AlunoDao;

public class ServiceAlunoDao extends AlunoDao {
    public ServiceAlunoDao(ServiceAluno serviceAluno) {
        super(serviceAluno);
    }
    public ServiceAlunoDao() {
    }
}
